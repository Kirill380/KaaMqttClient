package org.kaaproject.kaa.common.channels.protocols.mqtt.listeners;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface DeliveryCompleteListener {
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken);
}
