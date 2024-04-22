package com.mefrreex.jooq;

import cn.nukkit.plugin.PluginBase;

public class JOOQConnectorPNX extends PluginBase {

    @Override
    public void onLoad() {
        JOOQConnector.setJOOQMessagesEnabled(false);
    }
}
