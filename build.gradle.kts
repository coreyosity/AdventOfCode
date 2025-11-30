plugins {
    kotlin("jvm") version "2.1.0"
    id("com.diffplug.spotless") version "6.0.0"
    application
}

group = "aoc"
version = "1.0.0"

repositories {
    mavenCentral()
}

sourceSets {
    main {
        kotlin.srcDir("src/main/kotlin")
        resources.srcDir("src/main/resources")
    }
    test {
        kotlin.srcDir("src/test/kotlin")
    }
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
    
    test {
        useJUnitPlatform()
    }
    
    processResources {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    
    register<JavaExec>("runSolution") {
        group = "application"
        description = "Run an AoC solution (e.g., -PmainClass=aoc.y2024.Day01Kt)"
        classpath = sourceSets.main.get().runtimeClasspath
        mainClass.set(project.findProperty("mainClass") as String? ?: "aoc.y2024.Day01Kt")
    }
}

kotlin {
    jvmToolchain(11)
}

spotless {
    kotlin {
        target("src/**/*.kt")
        ktlint("0.41.0").userData(mapOf("indent_size" to "4", "continuation_indent_size" to "4"))
    }
}
