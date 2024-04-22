package com.mefrreex.jooq.database;

import com.mefrreex.jooq.JOOQ;

import java.sql.Connection;
import java.util.concurrent.CompletableFuture;

public interface IDatabase extends JOOQ {
    CompletableFuture<Connection> getConnection();
}
