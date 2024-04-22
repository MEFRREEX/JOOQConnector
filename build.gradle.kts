plugins {
    `java-library`
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.mefrreex.jooqconnector"
version = "2.2.1"
description = "JOOQConnector"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenLocal()
    maven("https://repo.maven.apache.org/maven2/")
    maven("https://jitpack.io")
    maven("https://repo.opencollab.dev/maven-releases/")
    maven("https://repo.opencollab.dev/maven-snapshots/")
}

dependencies {
    implementation("org.jooq:jooq:3.19.7")
    implementation("org.xerial:sqlite-jdbc:3.42.0.0")
    implementation("com.mysql:mysql-connector-j:8.3.0")
    compileOnly("com.github.PowerNukkitX:PowerNukkitX:master-SNAPSHOT")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.withType<ProcessResources> {
    filesMatching("*.yml") {
        expand(project.properties)
    }
}