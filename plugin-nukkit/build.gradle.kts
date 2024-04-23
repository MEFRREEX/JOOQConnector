plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    maven("https://repo.opencollab.dev/maven-releases/")
    maven("https://repo.opencollab.dev/maven-snapshots/")
}

dependencies {
    compileOnlyApi("cn.nukkit:nukkit:1.0-SNAPSHOT")
    api(project(":api"))
}

tasks.withType<Jar> {
    archiveFileName.set("JOOQConnector-Nukkit-${project.version}.jar")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}