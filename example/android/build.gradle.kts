// buildscript {
//     ext.kotlin_version = '1.8.21'
//     repositories {
//         google()
//         mavenCentral()
//     }

//     dependencies {
//         classpath 'com.android.tools.build:gradle:8.2.1'
//         classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
//     }
// }

// allprojects {
//     repositories {
//         google()
//         mavenCentral()
//     }
// }

// rootProject.buildDir = '../build'
// subprojects {
//     project.buildDir = "${rootProject.buildDir}/${project.name}"
// }
// subprojects {
//     project.evaluationDependsOn(':app')
// }

// tasks.register("clean", Delete) {
//     delete rootProject.buildDir
// }

plugins {
    kotlin("android") version "1.9.10" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val newBuildDir: Directory = rootProject.layout.buildDirectory.dir("../../build").get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}

subprojects {
    project.evaluationDependsOn(":app")
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
