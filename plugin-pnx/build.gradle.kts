plugins {
    id("java")
}

repositories {
    maven("https://jitpack.io")
    maven("https://repo.opencollab.dev/maven-releases/")
}

dependencies {
    compileOnlyApi("com.github.PowerNukkitX:PowerNukkitX:master-SNAPSHOT")
    api(project(":api"))
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.withType<Jar> {
    archiveFileName.set("JOOQConnector-PNX-${project.version}.jar")
}