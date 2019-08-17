
import io.qameta.allure.gradle.AllureExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.41"
    id("io.qameta.allure") version "2.8.1"
    id("com.adarshr.test-logger") version "1.7.0"
    id("io.gitlab.arturbosch.detekt") version "1.0.0-RC16"
}

// CodeQuality
val detektVersion = "1.0.0-RC16"

// Testing
val junitVersion = "5.3.1"
val ashotVersion = "1.5.4"
val jsoupVersion = "1.11.3"
val selenideVersion = "5.2.8"

// Reporting
val allureVersion = "2.12.1"

// Utils
val slf4jVersion = "1.7.25"
val log4j2Version = "2.12.1"
val log4j2KotlinApiVersion = "1.0.0"
val ownerVersion = "1.0.10"
val apacheCommonsVersion = "1.1"

group = "com.dkorobtsov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

configure<AllureExtension> {
    resultsDir = File(System.getProperty("user.dir"), "/allure-results")
    autoconfigure = true
    aspectjweaver = true
    version = allureVersion
    allureJavaVersion = allureVersion

    useJUnit5 {
        version = allureVersion
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    //Utils
    implementation("org.aeonbits.owner:owner-java8:$ownerVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4j2Version")
    implementation("org.apache.logging.log4j:log4j-api-kotlin:$log4j2KotlinApiVersion")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:$log4j2Version")
    implementation("org.apache.logging.log4j:log4j-jul:$log4j2Version")
    implementation("org.slf4j:jul-to-slf4j:$slf4jVersion")
    implementation("org.slf4j:slf4j-api:$slf4jVersion")

    //Testing
    implementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    implementation("org.jsoup:jsoup:$jsoupVersion")
    implementation("com.codeborne:selenide:$selenideVersion")
    implementation("ru.yandex.qatools.ashot:ashot:$ashotVersion")

    //Reporting
    implementation("io.qameta.allure:allure-java-commons:$allureVersion")
    implementation("io.qameta.allure:allure-attachments:$allureVersion")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.named<Test>("test") {
    include("**/test/**/*")
    //include("**/test/url/**/*")
    //include("**/test/smoke/**/*")
    //include("**/test/visual/**/*")
    useJUnitPlatform()
    outputs.upToDateWhen {
        false
    }

    testlogger {
        setTheme("mocha")
        showExceptions = true
        //slowThreshold = 1_000 (default is 2_000)
        showSummary = true
        showPassed = true
        showSkipped = true
        showFailed = true
        showStandardStreams = true
        showPassedStandardStreams = true
        showSkippedStandardStreams = true
        showFailedStandardStreams = true
    }
}

tasks.check {
    dependsOn("detekt")
}

detekt {
    reports {
        xml.enabled = false
        html.enabled = false
    }
    ignoreFailures = false
    toolVersion = detektVersion
    input = files("src/main/kotlin", "src/test/kotlin")
}