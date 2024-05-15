plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnlyApi("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
    api(project(":api"))
}

tasks.withType<Jar> {
    archiveFileName.set("JOOQConnector-Bukkit-${project.version}.jar")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}