package org.kaaproject.kaa.common.channels.protocols.mqtt.listeners.impl;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.kaaproject.kaa.common.channels.protocols.mqtt.listeners.DeliveryCompleteListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleDeliveryCompleteListener implements DeliveryCompleteListener {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleDeliveryCompleteListener.class);

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        LOG.info("Delivery completed");
    }
}
