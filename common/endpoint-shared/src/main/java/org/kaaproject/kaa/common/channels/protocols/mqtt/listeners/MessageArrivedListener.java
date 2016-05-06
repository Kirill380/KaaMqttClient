package org.kaaproject.kaa.common.channels.protocols.mqtt.listeners;

import org.eclipse.paho.client.mqttv3.MqttMessage;


public interface MessageArrivedListener {
    public void messageArrived(String topic, MqttMessage message);
}
