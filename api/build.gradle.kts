plugins {
    id("java")
}

version = "1.0.0"

repositories {
    mavenLocal()
    maven("https://repo.maven.apache.org/maven2/")
}

dependencies {
    implementation("org.jooq:jooq:3.19.7")
    implementation("org.xerial:sqlite-jdbc:3.42.0.0")
    implementation("com.mysql:mysql-connector-j:8.3.0")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
}

tasks.withType<Jar> {
    archiveFileName.set("JOOQConnector-API-$version.jar")
}