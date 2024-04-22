plugins {
    `java-library`
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.mefrreex.jooqconnector"
version = "1.0.1"
description = "JOOQConnector"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenLocal()
    maven("https://repo.maven.apache.org/maven2/")
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