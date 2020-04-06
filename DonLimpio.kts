#!/usr/bin/env kscript

//INCLUDE command/GradleCommands.kt
//INCLUDE command/IDECommands.kt
//INCLUDE command/ProjectStructureChanges.kt
//INCLUDE function/FileFunctions.kt
//INCLUDE function/GradleFunctions.kt
//INCLUDE function/SystemFunctions.kt
//INCLUDE function/ADBFunctions.kt
//INCLUDE function/IDEAFunctions.kt

import java.io.File
import java.io.IOException
import java.nio.file.Paths

/** Initializations */

val defaultVerboseIndentation = "     "
val verbose = true

/** Program flow */

var option : KleaningOption = selectMainMenuTask()
while (option == KleaningOption.UNDEFINED) {
    option = selectMainMenuTask()
    if (option == KleaningOption.EXIT) {
        System.exit(1) // 👋
    }
}

when (option) {
    KleaningOption.UNDEFINED -> TODO()
    KleaningOption.EXIT -> TODO()
    KleaningOption.PROJECT_STRUCTURE_CHANGED -> TODO()
    KleaningOption.GRADLE_UPGRADE -> {
        runCleanupForGradleUpdate() // GradleCommands.kt
    }
    KleaningOption.GRADLE_GLOBAL_CLEANUP -> TODO()
    KleaningOption.AGP_UPDATE -> {
        runCleanupForIDEUpdate() // IDECommands.kt
    }
    KleaningOption.INTELLIJ_ANDROID_STUDIO_GLOBAL_CLEANUP -> {
        TODO()
    }
}

/** Helper functions */

fun selectMainMenuTask(): KleaningOption {
    println("Pick one of the following options:")
    enumValues<KleaningOption>().forEach { option ->
        if (option != KleaningOption.UNDEFINED) {
            println("${option.value}. ${option.briefDescription}")
        }
    }
    println()

    val menuOption = "[0-${KleaningOption.values().size - 2}]".toRegex()
    val stdin : String? = readLine()
    println()

    return if (!menuOption.matches(stdin.toString())) {
        KleaningOption.UNDEFINED
    } else {
        KleaningOption.values()[stdin!!.toInt()]
    }
}

/** Helper components */
enum class KleaningOption(val value: Int, val briefDescription: String = "", val longDescription: String = "") {
    UNDEFINED(value = -1),
    PROJECT_STRUCTURE_CHANGED(value = 1, briefDescription = "TODO"),
    GRADLE_UPGRADE(value = 2, briefDescription = "Gradle-related stuff"),
    GRADLE_GLOBAL_CLEANUP(value = 3, briefDescription = "TODO"),
    AGP_UPDATE(value = 4, briefDescription = "IDEA or Android Studio (AGP) update"),
    INTELLIJ_ANDROID_STUDIO_GLOBAL_CLEANUP(value = 5, briefDescription = "TODO"),
    EXIT(value = 0, briefDescription = "Cancel"),
}


// R+D

//val gradleHome : File? = gradleHome()
//val gradleHome: File? = System.getenv("HOME")?.let { File("$it/.gradle") }
//var gradleHome : File? = System.getenv("HOME")?.let { File("/Users/user/.gradle") }
//var gradleHome :File =  File("/Users/user/.gradle")

// println("gradleHome: ${gradleHome!!.path}")
// println("gradleHome: ${gradleHome!!.absolutePath}")

//val userHome = File(System.getProperty("user.home"))
//val workingDir = File(Paths.get("").toAbsolutePath().toString())
//

//val wetRun = dryRun.not()

//fun Runtime.execOnWetRun(command: String) = if (wetRun) exec(command) else null

/*
println("Script is running with ${args.size} args passed")

for(arg in args) {
    println("arg: $arg")
}*/
