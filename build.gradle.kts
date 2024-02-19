plugins {
    id("java")
}

group = "com.sursindmitry"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }

    withType<Jar> {
        manifest {
            attributes["Main-Class"] = "com.sursindmitry.Main"
        }
    }

    test {
        useJUnitPlatform()
    }
}
