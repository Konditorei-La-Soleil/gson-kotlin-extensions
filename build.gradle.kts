plugins {
    kotlin("jvm") version "2.0.0"
    `maven-publish`
}

group = "moe.lasoleil"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    compileOnly("com.google.code.gson:gson:2.8.9")
    // https://mvnrepository.com/artifact/com.squareup.okio/okio
    compileOnly("com.squareup.okio:okio:3.10.2")

    testImplementation(kotlin("test"))
    testImplementation("com.google.code.gson:gson:2.8.9")
    testImplementation("com.squareup.okio:okio:3.10.2")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components.findByName("java"))
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
        }
    }
    repositories {
        mavenLocal()
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}