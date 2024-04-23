package com.mefrreex.jooq;

import org.jukeboxmc.api.plugin.Plugin;

public class JOOQConnectorJukeboxMC extends Plugin {

    @Override
    public void onStartup() {
        JOOQConnector.setJOOQMessagesEnabled(false);
    }

    @Override
    public void onEnable() {

    }
}
