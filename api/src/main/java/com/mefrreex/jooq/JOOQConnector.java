package com.mefrreex.jooq;

public class JOOQConnector {

    /**
     * Disable or enable JOOQ logo and tips printing
     * @param enabled Enable or disable
     */
    public static void setJOOQMessagesEnabled(boolean enabled) {
        String value = Boolean.toString(!enabled);
        System.setProperty("org.jooq.no-logo", value);
        System.setProperty("org.jooq.no-tips", value);
    }
}
