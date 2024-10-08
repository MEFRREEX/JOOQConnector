# JOOQConnector
A library for working with databases using JOOQ ORM for Java.

[![License: GNU GPLv3](https://img.shields.io/badge/License-GNU%20GPLv3-yellow)](LICENSE)
[![Version](https://img.shields.io/badge/Version-1.0.1-brightgreen)](https://github.com/MEFRREEX/JOOQConnector/releases/tag/1.0.1)
[![Jitpack](https://jitpack.io/v/MEFRREEX/JOOQConnector.svg)](https://jitpack.io/#MEFRREEX/JOOQConnector)

## 📖 Overview
**JOOQConnector** is a Java library designed for easy interaction with databases using the JOOQ ORM. It includes built-in support for SQLite and MySQL databases and is designed to work with various server software like Bukkit, Nukkit, PowerNukkitX, JukeboxMC, and WaterdogPE.

### ✨ Features
- **SQLite3 and MySQL Support**: Seamless integration with SQLite and MySQL databases.
- **No Unnecessary Logs**: Disable JOOQ logo and tips from appearing in the logs.
- **Bundled Drivers**: Includes SQLite, and MySQL drivers in the JAR.
- **Cross-Platform Support**: Compatible with different Minecraft server software.

## 🛠 Code Examples

### Disable JOOQ Logs
You can disable the printing of the JOOQ logo and tips:
```java
JOOQConnector.setJOOQMessagesEnabled(false);
```

### SQLite3 Example
```java
Table<?> table = DSL.table("test");
SQLiteDatabase database = new SQLiteDatabase(new File("database.db"));

// Creating table
database.getConnection().thenAcceptAsync(connection -> {
    DSL.using(connection)
            .createTableIfNotExists(table)
            .column("id", SQLDataType.INTEGER)
            .column("data", SQLDataType.VARCHAR)
            .unique("id")
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
    return result.isEmpty() ? null : result.get(0).get(DSL.field("data", String.class));
}).join();

System.out.println("Value from table: " + value);
```

### MySQL Example
```java
MySQLDatabase database = new MySQLDatabase("127.0.0.1:3306", "database", "user", "password");

// The rest of the code is identical to the SQLite example...
```

### Switching Between SQLite and MySQL
```java
IDatabase database = sqlite ? 
    new SQLiteDatabase(new File("database.db")) : 
    new MySQLDatabase("127.0.0.1:3306", "database", "user", "password");
```

### Organizing Database Operations in a Class
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
                    .unique("id")
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
                    .where(DSL.field("id").eq(id))
                    .fetch();

            return result.isEmpty() ? null : result.get(0).get(DSL.field("data", String.class));
        });
    }
}
```

## 🔌 Installation

### Plugin Setup
If you're not using the standalone API version, place the plugin JAR in your server's `plugins` folder.

### Maven
Add the following repository and dependency to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.MEFRREEX</groupId>
    <artifactId>JOOQConnector</artifactId>
    <version>1.0.1</version>
    <scope>provided</scope>
</dependency>
```

### Gradle
Add the following repository and dependency to your `build.gradle`:

```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.MEFRREEX:JOOQConnector:1.0.1'
}
```

___

[Switch to Russian](README_ru.md)