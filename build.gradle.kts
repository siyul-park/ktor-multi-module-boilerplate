import org.jetbrains.kotlin.konan.properties.Properties

val kotlin_version: String by project

buildscript {
    val klint_version: String by project

    repositories {
        maven { url = uri("https://plugins.gradle.org/m2/") }
        jcenter()
    }
    dependencies {
        classpath("org.jlleitschuh.gradle:ktlint-gradle:$klint_version")
    }
}

plugins {
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
}

group = "io.github.siyual_park"
version = "0.0.1-SNAPSHOT"

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    repositories {
        mavenLocal()
        jcenter()
    }

    dependencies {
        implementation(kotlin("stdlib"))
    }

    kotlin.sourceSets["main"].kotlin.srcDirs("src/main")
    kotlin.sourceSets["test"].kotlin.srcDirs("src/test")

    sourceSets["main"].resources.srcDirs("src/main/resources")
    sourceSets["test"].resources.srcDirs("src/test/resources")

    // Set package properties
    val generatedDir = "$buildDir/resources/generated"

    sourceSets {
        main {
            output.dir(generatedDir, "builtBy" to "generateProjectProperties")
        }
    }

    task("generateProjectProperties") {
        doLast {
            val propertiesFile = file("$generatedDir/project.properties")
            propertiesFile.parentFile.mkdirs()

            val properties = Properties()
            properties.setProperty(
                "version",
                if (project.version.toString() == "unspecified") {
                    rootProject.version.toString()
                } else {
                    project.version.toString()
                }
            )
            properties.setProperty(
                "group",
                if (project.group.toString() == "unspecified") {
                    rootProject.group.toString()
                } else {
                    project.group.toString()
                }
            )
            properties.setProperty(
                "name",
                if (project.name == "unspecified") {
                    rootProject.name
                } else {
                    project.name
                }
            )

            properties.store(propertiesFile.writer(), null)
        }
    }

    (tasks.getByName("processResources") as ProcessResources).setDependsOn(listOf(tasks.getByName("generateProjectProperties")))
}
