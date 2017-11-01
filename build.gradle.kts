plugins {
    id("org.jetbrains.kotlin.jvm") version "1.1.51"
    id("org.jetbrains.kotlin.plugin.spring") version "1.1.51"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.1.51"
    id("org.springframework.boot") version "1.5.8.RELEASE"
    id("io.spring.dependency-management") version "1.0.3.RELEASE"
}

group = "com.kapilkoju"
version = "0.0.1-SNAPSHOT"

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.springframework.boot:spring-boot-starter-data-jpa")
    compile("org.springframework.boot:spring-boot-starter-batch")
    compile("org.springframework.boot:spring-boot-devtools")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jre8")
    compile("org.jetbrains.kotlin:kotlin-reflect")
    compile("org.jsoup:jsoup:1.10.3")

    runtime("com.h2database:h2")

    testCompile("org.springframework.boot:spring-boot-starter-test")
}

repositories {
    mavenCentral()
    maven(url = "https://repo.spring.io/snapshot")
    maven(url = "https://repo.spring.io/milestone")
}