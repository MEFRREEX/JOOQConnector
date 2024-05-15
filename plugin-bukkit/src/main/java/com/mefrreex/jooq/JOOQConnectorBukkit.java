package com.mefrreex.jooq;

import org.bukkit.plugin.java.JavaPlugin;

public class JOOQConnectorBukkit extends JavaPlugin {

    @Override
    public void onLoad() {
        JOOQConnector.setJOOQMessagesEnabled(false);
    }
}
