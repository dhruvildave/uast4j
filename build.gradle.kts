plugins {
    idea
    application
    kotlin("jvm")
}

group = "dev.uast"
version = "latest"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("dev.uast.MainKt")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-class"] = "dev.uast.MainKt"
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

dependencies {
    implementation(kotlin("stdlib"))
}
