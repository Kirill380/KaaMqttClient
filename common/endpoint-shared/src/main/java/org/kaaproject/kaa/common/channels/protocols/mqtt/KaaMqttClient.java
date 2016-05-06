package org.kaaproject.kaa.common.channels.protocols.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.kaaproject.kaa.common.channels.protocols.mqtt.listeners.ConnectionLostListener;
import org.kaaproject.kaa.common.channels.protocols.mqtt.listeners.DeliveryCompleteListener;
import org.kaaproject.kaa.common.channels.protocols.mqtt.listeners.MessageArrivedListener;
import org.kaaproject.kaa.common.channels.protocols.mqtt.listeners.impl.SimpleConnectionLostListener;
import org.kaaproject.kaa.common.channels.protocols.mqtt.listeners.impl.SimpleDeliveryCompleteListener;
import org.kaaproject.kaa.common.channels.protocols.mqtt.listeners.impl.SimpleMessageArrivedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO properly handle MgttException
//TODO clientId what to set?
//TODO method call forbid
public class KaaMqttClient {
    private static final Logger LOG = LoggerFactory.getLogger(MqttClient.class);
    private final String SERVER_TOPIC = "server";
    private final String clientId;
    private final String broker;
    private String username;
    private String password;
    private MemoryPersistence persistence = new MemoryPersistence();
    private MqttClient client;
    private Integer qos = 2;
    private ConnectionLostListener connectionLostListener = new SimpleConnectionLostListener();
    private MessageArrivedListener messageArrivedListener = new SimpleMessageArrivedListener();
    private DeliveryCompleteListener deliveryCompleteListener = new SimpleDeliveryCompleteListener();


    public KaaMqttClient(String broker, String clientId) {
        this.broker = broker;
        this.clientId = clientId;
    }

    public KaaMqttClient(String broker, String clientId, String username, String password) {
        this.broker = broker;
        this.clientId = clientId;
        this.username = username;
        this.password = password;
    }

    public void connect() throws MqttException {
        client = new MqttClient(broker, clientId, persistence);
        client.connect(getOptions());
        client.subscribe(SERVER_TOPIC, qos);
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                connectionLostListener.connectionLost(cause);
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                messageArrivedListener.messageArrived(topic, message);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                deliveryCompleteListener.deliveryComplete(token);
            }
        });
    }


    public void disconnect() throws MqttException {
        client.disconnect();
    }

    public void sendRequest(byte[] body) throws MqttException {
        MqttMessage message = new MqttMessage();
        message.setPayload(body);
        message.setQos(qos);
        client.publish("client/" + clientId, message);
    }


    public void onMessageArrived(MessageArrivedListener messageArrivedListener) {
        this.messageArrivedListener = messageArrivedListener;
    }

    public void onConnectionLost(ConnectionLostListener connectionLostListener) {
        this.connectionLostListener = connectionLostListener;
    }

    public void onDeliveryComplete(DeliveryCompleteListener deliveryCompleteListener) {
        this.deliveryCompleteListener = deliveryCompleteListener;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }

    private MqttConnectOptions getOptions() {
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);

        if (username != null && password != null) {
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());
        }

        return connOpts;
    }
}
