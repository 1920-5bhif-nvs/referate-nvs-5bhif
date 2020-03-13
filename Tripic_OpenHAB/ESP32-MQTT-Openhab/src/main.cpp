#include <Arduino.h>
#include <WiFi.h>
#include <PubSubClient.h>
#include <DHT.h>
#include <string>
#include <NTPClient.h>

void measure();

//credentials for wifi
const char *ssid = "YOUR-SSID";
const char *pass = "YOUR-PASS";

//credentials for mqtt-broker
const char *brokerIP = "MQTT-BROKER-IP";
const char *brokerUser = "user";
const char *brokerPass = "pass";
const int mqttPort = 1883;

//clients
WiFiClient espClient;
PubSubClient client(espClient);
WiFiUDP ntpUDP;

//dht22 - sensor
#define DHTTYPE DHT22
#define DHTPIN 14 //the digital pin to read from
DHT dht(DHTPIN, DHTTYPE);
const int timeToWait = 10000; //in ms; at least 250ms; Wait a few seconds between measurements.

//NTP - for timestamping the published values
NTPClient timeClient(ntpUDP, "europe.pool.ntp.org", 3600, 60000);

void setupWifi()
{
  delay(100);
  Serial.print("\nConnecting to ");
  Serial.println(ssid);

  WiFi.begin(ssid, pass);

  while (WiFi.status() != WL_CONNECTED)
  {
    delay(100);
    Serial.print(".");
  }

  Serial.print("\nConnected to ");
  Serial.println(ssid);
  timeClient.begin();
  WiFi.setSleep(false);        // disables the sleep mode of the esp32
  WiFi.setAutoReconnect(true); //if disconnected from Wifi, try reconnect
}

void reconnect()
{
  while (!client.connected())
  {
    Serial.print("\n Connecting to ");
    Serial.println(brokerIP);
    if (client.connect("admin", brokerUser, brokerPass))
    {
      Serial.print("\nConnected to ");
      Serial.println(brokerIP);

      char time[50];
      snprintf(time, sizeof(time), "ESP32 connected: %s", timeClient.getFormattedTime());
      client.publish("connected devices", time);
    }
    else
    {
      Serial.println("Trying to connect to MQTT-Broker again");
      delay(5000);
    }
  }
}

void setup()
{
  Serial.begin(115200);
  setupWifi();
  client.setServer(brokerIP, mqttPort);
  dht.begin();
}

void loop()
{
  if (!client.connected())
  {
    reconnect();
  }
  timeClient.update();
  measure();
  client.loop();
}

void measure()
{
  // Wait a few seconds between measurements.
  delay(timeToWait);
  // Reading temperature or humidity takes about 250 milliseconds!
  // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
  float h = dht.readHumidity();
  // Read temperature as Celsius (the default) - if you pass true as a parameter you will get fahrenheit!
  float t = dht.readTemperature();

  char temperature[16];
  snprintf(temperature, sizeof(temperature), "%.1f °C", t);

  char humidity[16];
  snprintf(humidity, sizeof(humidity), "%.1f %%", h);

  // Check if any reads failed and exit early (to try again).
  if (isnan(h) || isnan(t))
  {
    Serial.println(F("Failed to read from DHT sensor!"));
    return;
  }

  client.publish("temperature", temperature);
  client.publish("humidity", humidity);
  char timestamp[50];
  snprintf(timestamp, sizeof(timestamp), "%s", timeClient.getFormattedTime());
  client.publish("last update", timestamp);
}