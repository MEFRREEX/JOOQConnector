plugins {
    id("java")
}

repositories {
    maven("https://repo.opencollab.dev/maven-releases/")
    maven("https://repo.opencollab.dev/maven-snapshots/")
}

dependencies {
    compileOnlyApi("cn.nukkit:nukkit:1.0-SNAPSHOT")
    api(project(":api"))
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.withType<Jar> {
    archiveFileName.set("JOOQConnector-Nukkit-${project.version}.jar")
}