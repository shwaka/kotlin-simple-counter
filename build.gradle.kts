plugins {
    kotlin("multiplatform") version "1.4.32"
    id("io.kotest") version "0.2.6"
    id("com.adarshr.test-logger") version "2.1.1"
    `maven-publish`
}

group = "com.github.shwaka.counter"
version = "0.2"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js(BOTH) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }


    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                // kotest
                val version = "4.3.2"
                implementation("io.kotest:kotest-runner-junit5:$version")
                implementation("io.kotest:kotest-assertions-core:$version")
                implementation("io.kotest:kotest-property:$version")
                implementation("io.kotest:kotest-assertions-compiler:$version")
            }
        }
        val jsMain by getting
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val nativeMain by getting
        val nativeTest by getting
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

testlogger {
    theme = com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA
    showCauses = true
    showStandardStreams = true
    // showFullStackTraces = true
}

publishing {
    repositories {
        maven {
            url = uri("../maven/repository")
            name = "MyMaven"
        }
    }
}
