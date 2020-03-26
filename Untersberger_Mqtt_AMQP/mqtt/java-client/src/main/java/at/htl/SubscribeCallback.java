package at.htl;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SubscribeCallback implements MqttCallback {

	public void connectionLost(Throwable cause) {
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
    System.out.println(topic + " " + new String(message.getPayload()));
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
	}
}

