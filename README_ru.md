# JOOQConnector
Библиотека для работы с базами данных с использованием ORM JOOQ для Java.

[![Лицензия: GNU GPLv3](https://img.shields.io/badge/License-GNU%20GPLv3-yellow)](LICENSE)
[![Версия](https://img.shields.io/badge/Version-1.0.1-brightgreen)](https://github.com/MEFRREEX/JOOQConnector/releases/tag/1.0.1)
[![Jitpack](https://jitpack.io/v/MEFRREEX/JOOQConnector.svg)](https://jitpack.io/#MEFRREEX/JOOQConnector)

## 📖 Обзор
**JOOQConnector** — это библиотека для Java, предназначенная для удобной работы с базами данных через ORM JOOQ. Она поддерживает SQLite и MySQL, а также рассчитана для работы с различными серверными ядрами, таких как Bukkit, Nukkit, PowerNukkitX, JukeboxMC и WaterdogPE.

### ✨ Возможности
- **Поддержка SQLite3 и MySQL**: Бесшовная интеграция с базами данных SQLite и MySQL.
- **Отключение логов**: Возможность отключить вывод логотипа и подсказок JOOQ в логах.
- **Встроенные драйверы**: Включает драйверы SQLite и MySQL в JAR-файл.
- **Кроссплатформенная поддержка**: Совместимость с различными программными серверами Minecraft.

## 🛠 Примеры кода

### Отключение логов JOOQ
Вы можете отключить вывод логотипа и подсказок JOOQ:
```java
JOOQConnector.setJOOQMessagesEnabled(false);
```

### Пример работы с SQLite3
```java
Table<?> table = DSL.table("test");
SQLiteDatabase database = new SQLiteDatabase(new File("database.db"));

// Создание таблицы
database.getConnection().thenAcceptAsync(connection -> {
    DSL.using(connection)
            .createTableIfNotExists(table)
            .column("id", SQLDataType.INTEGER)
            .column("data", SQLDataType.VARCHAR)
            .unique("id")
            .execute();
}).join();

// Вставка значения
database.getConnection().thenAcceptAsync(connection -> {
    DSL.using(connection).insertInto(table)
            .set(DSL.field("id"), 1)
            .set(DSL.field("data"), "Test string")
            .execute();
}).join();

// Получение значения
String value = database.getConnection().thenApplyAsync(connection -> {
    Result<Record> result = DSL.using(connection).select()
            .from(table)
            .where(DSL.field("id").eq(1))
            .fetch();
    return result.isEmpty() ? null : result.get(0).get(DSL.field("data", String.class));
}).join();

System.out.println("Value from table: " + value);
```

### Пример работы с MySQL
```java
MySQLDatabase database = new MySQLDatabase("127.0.0.1:3306", "database", "user", "password");

// Остальной код идентичен примеру с SQLite...
```

### Переключение между SQLite и MySQL
```java
IDatabase database = sqlite ? 
    new SQLiteDatabase(new File("database.db")) : 
    new MySQLDatabase("127.0.0.1:3306", "database", "user", "password");
```

### Организация операций с базой данных в классе
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

## 🔌 Установка

### Установка плагина
Если вы не используете версию API для самостоятельного использования, поместите JAR-файл плагина в папку `plugins` вашего сервера.

### Maven
Добавьте следующий репозиторий и зависимость в ваш `pom.xml`:

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
Добавьте следующий репозиторий и зависимость в ваш `build.gradle`:

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

[Переключиться на английский](README.md)