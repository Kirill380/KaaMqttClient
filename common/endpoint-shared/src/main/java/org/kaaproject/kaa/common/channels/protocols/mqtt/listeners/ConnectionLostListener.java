package org.kaaproject.kaa.common.channels.protocols.mqtt.listeners;


public interface ConnectionLostListener {
    public void connectionLost(Throwable e);
}
