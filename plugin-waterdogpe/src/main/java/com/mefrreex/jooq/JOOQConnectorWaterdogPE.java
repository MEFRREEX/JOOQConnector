package com.mefrreex.jooq;

import dev.waterdog.waterdogpe.plugin.Plugin;

public class JOOQConnectorWaterdogPE extends Plugin {

    @Override
    public void onStartup() {
        JOOQConnector.setJOOQMessagesEnabled(false);
    }

    @Override
    public void onEnable() {

    }
}
