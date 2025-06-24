plugins {
    kotlin("jvm") version "2.0.0"
    `maven-publish`
}

group = "moe.lasoleil"
version = "0.2.0"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    compileOnly("com.google.code.gson:gson:2.8.9")
    // https://mvnrepository.com/artifact/com.squareup.okio/okio
    compileOnly("com.squareup.okhttp3:okhttp:4.12.0")
    // https://mvnrepository.com/artifact/io.netty/netty-buffer
    compileOnly("io.netty:netty-buffer:4.1.122.Final")

    testImplementation(kotlin("test"))
    testImplementation("com.google.code.gson:gson:2.8.9")
    testImplementation("com.squareup.okhttp3:okhttp:4.12.0")
    testImplementation("io.netty:netty-buffer:4.1.122.Final")
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