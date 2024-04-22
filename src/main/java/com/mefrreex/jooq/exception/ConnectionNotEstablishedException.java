package com.mefrreex.jooq.exception;

public class ConnectionNotEstablishedException extends RuntimeException {

    public ConnectionNotEstablishedException() {
        super("Connection not established");
    }

    public ConnectionNotEstablishedException(Throwable throwable) {
        super("Connection not established", throwable);
    }
}
