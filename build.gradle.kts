plugins {
    `java-library`
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

java.sourceCompatibility = JavaVersion.VERSION_17

tasks.build {
    dependsOn(tasks.shadowJar)
}

allprojects {

    group = "com.mefrreex.jooqconnector"
    version = "1.0.0"
    description = "JOOQConnector"

    apply {
        plugin("com.github.johnrengelman.shadow")
        plugin("java-library")
    }

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

    tasks.withType<ProcessResources> {
        filesMatching("*.yml") {
            expand(project.properties)
        }
    }
}