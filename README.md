# JOOQConnector
Library for working with database using JOOQ

[![License: GNU GPLv3](https://img.shields.io/badge/License-%20%20GNU%20GPLv3%20-yellow)](LICENSE)
[![Version](https://img.shields.io/badge/Version-1.0.0-brightgreen)](https://github.com/MEFRREEX/FormConstructor/releases/tag/2.0.1)
[![Jitpack](https://jitpack.io/v/MEFRREEX/JOOQConnector.svg)](https://jitpack.io/#MEFRREEX/JOOQConnector)

## 🛠 Examples

If you are using a standalone api, you can disable the printing of logo and tips when you run the rpogram
```java
JOOQConnector.setJOOQMessagesEnabled(false);
```

Example of using SQLite3 database
```java
Table<?> table = DSL.table("test");
SQLiteDatabase database = new SQLiteDatabase(new File("database.db"));

// Creating table
database.getConnection().thenAcceptAsync(connection -> {
    DSL.using(connection)
            .createTableIfNotExists(table)
            .column("id", SQLDataType.INTEGER)
            .column("data", SQLDataType.VARCHAR)
            .unique("id") // Value of this column will be unique
            .execute();
}).join();

// Inserting value into the table
database.getConnection().thenAcceptAsync(connection -> {
    DSL.using(connection).insertInto(table)
            .set(DSL.field("id"), 1)
            .set(DSL.field("data"), "Test string")
            .execute();
}).join();

// Getting value from the table
String value = database.getConnection().thenApplyAsync(connection -> {
    Result<Record> result = DSL.using(connection).select()
            .from(table)
            .where(DSL.field("id").eq(1))
            .fetch();

    // Get the value with index 0
    if (!result.isEmpty()) {
        return result.get(0).get(DSL.field("data", String.class));
    }
    return null;
}).join();

System.out.println("Value from table: " + value);
```

Example of using a MySQL database
```java
MySQLDatabase database = new MySQLDatabase("host", "database", "user", "password");

// Other code will be identical...
```

Example of using SQlite or MySQL database
```java
IDatabase database = sqlite ? 
    new SQLiteDatabase(new File("database.db")) : 
    new MySQLDatabase("host", "database", "user", "password");
```

You can make a separate class with methods for the database
```java
public class Database {

    private final IDatabase database;
    private final Table<?> table;

    public Database(IDatabase database, Table<?> table) {
        this.database = database;
        this.table = table;

        database.getConnection().thenAcceptAsync(connection -> {
            DSL.using(connection)
                    .createTableIfNotExists(table)
                    .column("id", SQLDataType.INTEGER)
                    .column("data", SQLDataType.VARCHAR)
                    .unique("id") // Value of this column will be unique
                    .execute();
        }).join();
    }

    public CompletableFuture<Void> addValue(int id, String data) {
        return database.getConnection().thenAcceptAsync(connection -> {
            DSL.using(connection).insertInto(table)
                    .set(DSL.field("id"), id)
                    .set(DSL.field("data"), data)
                    .execute();
        });
    }

    public CompletableFuture<String> getValue(int id) {
        return database.getConnection().thenApplyAsync(connection -> {
            Result<Record> result = DSL.using(connection).select()
                    .from(table)
                    .where(DSL.field("id").eq(1))
                    .fetch();

            // Get the value with index 0
            if (!result.isEmpty()) {
                return result.get(0).get(DSL.field("data", String.class));
            }
            return null;
        });
    }

    public static void main(String[] args) {
        Database database = new Database(
                new SQLiteDatabase(new File("database.db")),
                DSL.table("test")
        );

        // Inserting value into the table
        database.addValue(1, "Test string").join();

        // Getting value from the table
        String value = database.getValue(1).join();
        System.out.println("Value from table: " + value);
    }
}
```

## 🔌 Maven

#### Repository

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

#### Dependency
```xml
<dependency>
    <groupId>com.github.MEFRREEX</groupId>
    <artifactId>JOOQConnector</artifactId>
    <version>1.0.0</version>
</dependency>
```
