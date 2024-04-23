package com.mefrreex.jooq.database;

import com.mefrreex.jooq.exception.ConnectionNotEstablishedException;
import org.jooq.SQLDialect;
import lombok.Getter;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

@Getter
public class MySQLDatabase implements IDatabase {

    private final String host;
    private final String database;
    private final String user;
    private final String password;
    private Connection connection;

    public MySQLDatabase(String host, String database, String user, String password) {
        this.host = host.contains(":") ? host : host + ":3306";
        this.database = database;
        this.user = user;
        this.password = password;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to find MySQL driver", e);
        }
    }

    @Override
    public CompletableFuture<Connection> getConnection() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                if (connection == null || connection.isClosed()) {
                    String password = URLEncoder.encode(this.password, StandardCharsets.UTF_8);
                    String url = "jdbc:mysql://" + user + ":" + password + "@" + host + "/" + database;
                    connection = DriverManager.getConnection(url, user, password);
                }
                return connection;
            } catch (SQLException e) {
                throw new ConnectionNotEstablishedException(e);
            }
        });
    }

    @Override
    public SQLDialect dialect() {
        return SQLDialect.MYSQL;
    }
}