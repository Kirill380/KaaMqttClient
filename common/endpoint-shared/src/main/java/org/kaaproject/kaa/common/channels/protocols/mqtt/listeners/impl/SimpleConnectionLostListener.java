package org.kaaproject.kaa.common.channels.protocols.mqtt.listeners.impl;


import org.kaaproject.kaa.common.channels.protocols.mqtt.listeners.ConnectionLostListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleConnectionLostListener implements ConnectionLostListener {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleConnectionLostListener.class);

    @Override
    public void connectionLost(Throwable e) {
        LOG.info("Connection Lost");
    }
}
