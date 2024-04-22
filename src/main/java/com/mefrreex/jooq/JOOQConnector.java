package com.mefrreex.jooq;

import cn.nukkit.plugin.PluginBase;

public class JOOQConnector extends PluginBase {

    @Override
    public void onLoad() {
        System.setProperty("org.jooq.no-logo", "true");
        System.setProperty("org.jooq.no-tips", "true");
    }

    @Override
    public void onEnable() {

    }
}
