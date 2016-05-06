package org.kaaproject.kaa.common.channels.protocols.mqtt.listeners.impl;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.kaaproject.kaa.common.channels.protocols.mqtt.listeners.MessageArrivedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SimpleMessageArrivedListener implements MessageArrivedListener {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleMessageArrivedListener.class);

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        LOG.info("Message arrived");
    }
}
