package com.fiap.iot.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Getter
public class MqttConnection {

	private String broker = "tcp://broker.hivemq.com:1883";
	private String clientId = "clientId-service";
	private MqttClient client;
	private static MqttConnection instance;

	public void connectMqtt() {

		MemoryPersistence persistence = new MemoryPersistence();

		try {
			System.out.println(persistence);
			client = new MqttClient(broker, clientId, persistence);

			MqttConnectOptions connOpts = new MqttConnectOptions();
//			connOpts.setUserName("emqx_test");
//			connOpts.setPassword("emqx_test_password".toCharArray());
			connOpts.setCleanSession(true);

			client.setCallback(new OnMessageCallback());

			System.out.println("Connecting to broker: " + broker);
			client.connect(connOpts);
			System.out.println("Connected");

		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}

	public void pub(String pubTopic, String content, int qos) {
		try {
			MqttMessage message = new MqttMessage(content.getBytes());
			message.setQos(qos);
			client.publish(pubTopic, message);
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}

	public void sub(String subTopic, int qos) {
		try {
			client.subscribe(subTopic, qos);
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			client.disconnect();
			System.out.println("Disconnected");
			client.close();
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}
	
	public static MqttConnection getInstance() {
        if (instance == null) {
            instance = new MqttConnection();
            instance.connectMqtt();
        }
        return instance;
    }
}
