@file:Suppress("LocalVariableName", "PropertyName")

import org.gradle.language.base.internal.plugins.CleanRule
import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.2.0"

    repositories {
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin", kotlin_version))
    }
}

val kotlin_version: String by extra

group = "xyz.nightfury"
version = "1.0"

apply {
    plugin("kotlin2js")
    plugin("idea")
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    compile(kotlinModule("stdlib-js", kotlin_version))
    compile("org.jetbrains.kotlinx:kotlinx-html-js:0.6.8")
}

val mainSourceSet = the<JavaPluginConvention>().sourceSets["main"]!!

tasks {
    "compileKotlin2Js"(Kotlin2JsCompile::class) {
        kotlinOptions {
            main = "main.js"
            noStdlib = false
            verbose = false
            outputFile = "${mainSourceSet.output.resourcesDir}/$main"
            sourceMap = true
            metaInfo = true
            friendModulesDisabled = false
        }
    }

    val organizeResources by creating {
        group = "build"
        description = "Reorganizes resources to compile correctly."

        val outputDir = file("$buildDir/libraries")
        val compileClasspath = configurations["compileClasspath"]

        inputs.property("compileClasspath", compileClasspath)

        outputs.dir(outputDir)

        doLast {
            val kotlinStdLibJar = compileClasspath.single { it.name matches Regex("kotlin-stdlib-js-.+\\.jar") }
            val kotlinxHtmlJar = compileClasspath.single { it.name matches Regex("kotlinx-html-js-.+\\.jar") }

            copy {
                includeEmptyDirs = false
                from(zipTree(kotlinStdLibJar))
                from(zipTree(kotlinxHtmlJar))
                into(outputDir)
                include("**/*.js")
                exclude("META-INF/**")
            }

            copy {
                includeEmptyDirs = false
                from(outputDir)
                from("$buildDir/html")
                from(mainSourceSet.output) {
                    exclude("**/*.kjsm")
                }
                into("$buildDir/www/html")
            }
        }
    }

    "assemble" {
        dependsOn(organizeResources)
    }

    val buildChildren by creating {
        group = "build"
        description = "Builds all child modules."
        childProjects.values.forEach {
            dependsOn(it.getTasksByName("build", true)?.first())
        }
    }

    val runHTMLJar by creating {
        group = "build"
        description = "Runs the HTML Generator Jar."

        assert(childProjects.containsKey(":HTML")) { "No HTML module detected!" }

        doLast {
            exec {
                setWorkingDir(file("${project(":HTML").buildDir}/libs/"))
                commandLine("java", "-jar", "NightFuryHTML.jar")
            }
        }
    }

    "build" {
        dependsOn("clean")
        dependsOn(buildChildren)
        dependsOn(runHTMLJar)
        val compileKotlin2Js = dependsOn("compileKotlin2Js")

        buildChildren.mustRunAfter("clean")
        runHTMLJar.mustRunAfter(buildChildren)
        compileKotlin2Js.mustRunAfter(runHTMLJar)
    }
}