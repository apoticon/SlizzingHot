plugins {
    kotlin("jvm") version "1.8.21"
    application
}

group = "me.apoticon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.testng:testng:7.1.0")
    testImplementation ("junit:junit:4.13.2")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}
tasks.withType<Test>{
    useJUnitPlatform()
}