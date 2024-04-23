plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    maven("https://repo.jukeboxmc.eu/releases")
    maven("https://repo.jukeboxmc.eu/snapshots")
}

dependencies {
    compileOnlyApi("org.jukeboxmc:JukeboxMC-API:1.0.0-SNAPSHOT")
    api(project(":api"))
}

tasks.withType<Jar> {
    archiveFileName.set("JOOQConnector-JukeboxMC-${project.version}.jar")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}