package com.mefrreex.jooq;

import cn.nukkit.plugin.PluginBase;

public class JOOQConnectorNukkit extends PluginBase {

    @Override
    public void onLoad() {
        JOOQConnector.setJOOQMessagesEnabled(false);
    }
}
