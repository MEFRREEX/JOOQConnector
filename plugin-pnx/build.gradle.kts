plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    maven("https://jitpack.io")
    maven("https://repo.opencollab.dev/maven-releases/")
}

dependencies {
    compileOnlyApi("com.github.PowerNukkitX:PowerNukkitX:master-SNAPSHOT")
    api(project(":api"))
}

tasks.withType<Jar> {
    archiveFileName.set("JOOQConnector-PNX-${project.version}.jar")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}