
import org.jetbrains.kotlin.konan.properties.Properties

val ktor_version: String by project
val logback_version: String by project
val ktor_open_api_version: String by project

plugins {
    application
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    maven { url = uri("https://kotlin.bintray.com/ktor") }
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")

    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation("com.github.papsign:Ktor-OpenAPI-Generator:$ktor_open_api_version")

    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src/main")
kotlin.sourceSets["test"].kotlin.srcDirs("src/test")

sourceSets["main"].resources.srcDirs("src/main/resources")
sourceSets["test"].resources.srcDirs("src/test/resources")

val generatedDir = "$buildDir/generated"

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
        properties.setProperty("version", rootProject.version.toString())
        properties.store(propertiesFile.writer(), null)
    }
}

(tasks.getByName("processResources") as ProcessResources).setDependsOn(listOf(tasks.getByName("generateProjectProperties")))
