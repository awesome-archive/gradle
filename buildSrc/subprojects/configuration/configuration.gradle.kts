plugins {
    `java-gradle-plugin`
}

apply { plugin("org.gradle.kotlin.kotlin-dsl") }

dependencies {
    implementation(project(":kotlinDsl"))
}

gradlePlugin {
    (plugins) {
        "availableJavaInstallations" {
            id = "gradlebuild.available-java-installations"
            implementationClass = "org.gradle.gradlebuild.java.AvailableJavaInstallationsPlugin"
        }
    }
}
