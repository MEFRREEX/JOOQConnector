plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("maven-publish")
}

version = "1.0.2"

repositories {
    mavenLocal()
    maven("https://repo.maven.apache.org/maven2/")
    maven("https://jitpack.io")
    maven("https://repo.opencollab.dev/maven-snapshots/")
}

dependencies {
    compileOnly("com.github.PowerNukkitX:PowerNukkitX:master-SNAPSHOT")
    compileOnly("org.cloudburstmc.netty:netty-transport-raknet:1.0.0.CR1-SNAPSHOT")
    implementation(project(":api"))
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.withType<ProcessResources> {
    filesMatching("*.yml") {
        expand(project.properties)
    }
}

tasks.withType<Jar> {
    archiveFileName.set("JOOQConnector-PNX-$version.jar")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.mefrreex.jooqconnector"
                artifactId = "JOOQConnector-PNX"
                version = "1.0.2"
            }
        }
    }
}