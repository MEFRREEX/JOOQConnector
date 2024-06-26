package com.mefrreex.jooq.database;

import com.mefrreex.jooq.exception.ConnectionNotEstablishedException;
import org.jooq.SQLDialect;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

@Getter
public class SQLiteDatabase implements IDatabase {

    private final File file;
    private Connection connection;

    public SQLiteDatabase(File file) {
        this.file = file;
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    throw new IOException("Failed to create the database file");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to find SQLite driver", e);
        }
    }

    @Override
    public CompletableFuture<Connection> getConnection() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
                }
                return connection;
            } catch (SQLException e) {
                throw new ConnectionNotEstablishedException(e);
            }
        });
    }

    @Override
    public SQLDialect dialect() {
        return SQLDialect.SQLITE;
    }
}