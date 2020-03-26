package at.htl;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( final String[] args )
    {
        System.out.println( "Hello World!" );

        try {
           final MqttClient mqttClient = new MqttClient("tcp://localhost:1883", "client");
           final MqttConnectOptions connectOptions = new MqttConnectOptions();
           //connectOptions.setCleanSession(true);
           mqttClient.connect(connectOptions);
           mqttClient.setCallback(new SubscribeCallback());
           mqttClient.subscribe("#");
        } catch (final MqttException e) {
          e.printStackTrace();
        }    
    }
}
