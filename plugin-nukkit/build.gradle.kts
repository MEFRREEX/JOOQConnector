plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("maven-publish")
}

version = "1.0.2"

repositories {
    mavenLocal()
    maven("https://repo.maven.apache.org/maven2/")
    maven("https://repo.opencollab.dev/maven-releases/")
    maven("https://repo.opencollab.dev/maven-snapshots/")
}

dependencies {
    compileOnly("cn.nukkit:nukkit:1.0-SNAPSHOT")
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
    archiveFileName.set("JOOQConnector-Nukkit-$version.jar")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.mefrreex.jooqconnector"
                artifactId = "JOOQConnector-Nukkit"
                version = "1.0.2"
            }
        }
    }
}