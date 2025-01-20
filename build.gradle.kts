import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    idea
    application
    kotlin("jvm") version "2.1.0"
}

group = "dev.uast"
version = "latest"
val mainClassName = "${group}.MainKt"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        languageVersion.set(KotlinVersion.KOTLIN_2_1)
    }
}

application {
    mainClass.set(mainClassName)
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-class"] = mainClassName
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)

    from({
        configurations.runtimeClasspath.get().filter {
            it.name.endsWith("jar")
        }.map {
            zipTree(it)
        }
    })
}
