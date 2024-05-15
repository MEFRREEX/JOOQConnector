# JOOQConnector

[![License: GNU GPLv3](https://img.shields.io/badge/License-%20%20GNU%20GPLv3%20-yellow)](LICENSE)
[![Version](https://img.shields.io/badge/Version-1.0.1-brightgreen)](https://github.com/MEFRREEX/JOOQConnector/releases/tag/1.0.1)
[![Jitpack](https://jitpack.io/v/MEFRREEX/JOOQConnector.svg)](https://jitpack.io/#MEFRREEX/JOOQConnector)

## 🤔 About
Library for working with database using JOOQ

Features:
- SQLite3 support
- MySQL support
- Disabled printing logo and tips from JOOQ
- Inclusion of JOOQ, SQLite and MySQL drivers in jar file
- Support for different server software (Bukkit, Nukkit, PowerNukkitX, JukeboxMC, WaterdogPE)

## 🛠 Examples

If you are using a standalone api, you can disable the printing of logo and tips when you run the program
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

    if (!result.isEmpty()) {
        // Get the value with index 0
        return result.get(0).get(DSL.field("data", String.class));
    }
    return null;
}).join();

System.out.println("Value from table: " + value);
```

Example of using a MySQL database
```java
MySQLDatabase database = new MySQLDatabase("127.0.0.1:3306", "database", "user", "password");

// Other code will be identical...
```

Example of using SQlite or MySQL database
```java
IDatabase database = sqlite ? 
    new SQLiteDatabase(new File("database.db")) : 
    new MySQLDatabase("127.0.0.1:3306", "database", "user", "password");
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

            if (!result.isEmpty()) {
                // Get the value with index 0
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

## 🔌 Installation

If you are not using the standalone api version place the plugin of the appropriate version (for your software) in the `plugins` folder.


### Maven

Repository:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Dependency:
```xml
<dependency>
    <groupId>com.github.MEFRREEX</groupId>
    <artifactId>JOOQConnector</artifactId>
    <version>1.0.1</version>
    <scope>provided</scope>
</dependency>
```

### Gradle
Repository:
```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}
```
Dependency:
```groovy
dependencies {
    implementation 'com.github.MEFRREEX:JOOQConnector:1.0.1'
}
```
