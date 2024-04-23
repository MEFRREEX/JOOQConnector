plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
    maven("https://repo.waterdog.dev/main")
    maven("https://repo.opencollab.dev/maven-releases")
    maven("https://repo.opencollab.dev/maven-snapshots")
}

dependencies {
    compileOnlyApi("dev.waterdog.waterdogpe:waterdog:2.0.0-SNAPSHOT")
    api(project(":api"))
}

tasks.withType<Jar> {
    archiveFileName.set("JOOQConnector-WaterdogPE-${project.version}.jar")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}