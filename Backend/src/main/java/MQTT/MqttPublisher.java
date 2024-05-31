package MQTT;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttPublisher {
    public static void main(String[] args) {
            // Ensure the necessary arguments are provided
            String topic = "test";
            String messageContent = "testmessage";

            String brokerUrl = "tcp://localhost:1883";
            String clientId = MqttClient.generateClientId();

            try {
                MqttClient client = new MqttClient(brokerUrl, clientId);
                MqttConnectOptions options = new MqttConnectOptions();
                options.setCleanSession(true);

                System.out.println("Connecting to broker: " + brokerUrl);
                client.connect(options);
                System.out.println("Connected");

                MqttMessage message = new MqttMessage(messageContent.getBytes());
                message.setQos(2);  // Set QoS level to 2

                System.out.println("Publishing message: " + messageContent);
                client.publish(topic, message);
                System.out.println("Message published");

                client.disconnect();
                System.out.println("Disconnected");
            } catch (MqttException me) {
                me.printStackTrace();
            }
        }
        public static void sendmessage(String topic, String messageContent) {
            String brokerUrl = "tcp://localhost:1883";
            String clientId = MqttClient.generateClientId();

            try {
                MqttClient client = new MqttClient(brokerUrl, clientId);
                MqttConnectOptions options = new MqttConnectOptions();
                options.setCleanSession(true);

                System.out.println("Connecting to broker: " + brokerUrl);
                client.connect(options);
                System.out.println("Connected");

                MqttMessage message = new MqttMessage(messageContent.getBytes());
                message.setQos(2);  // Set QoS level to 2

                System.out.println("Publishing message: " + messageContent);
                client.publish(topic, message);
                System.out.println("Message published");

                client.disconnect();
                System.out.println("Disconnected");
            } catch (MqttException me) {
                me.printStackTrace();
            }
        }
    }