#!/usr/bin/env kscript

import java.io.File
import java.io.IOException
import java.nio.file.Paths

/** Initializations */

//val wetRun = dryRun.not()


/** Program flow */
var option : CleaningOption = selectMRCleanTask()
while (option == CleaningOption.UNDEFINED) {
    option = selectMRCleanTask()
}
if (option == CleaningOption.EXIT) {
    System.exit(1) // 💥
}

println("Well done bro.")



//val userHome = File(System.getProperty("user.home"))
//assert(userHome.exists()) { "Unable to determine the user home folder, aborting..." }
//println(userHome) //     /Users/cdiezsanchez


//val gradleHome = locateGradleHome()
// println(gradleHome) //   /Users/cdiezsanchez/.sdkman/candidates/gradle/current

val workingDir = File(Paths.get("").toAbsolutePath().toString())
println(workingDir) //   /Users/cdiezsanchez/Workspace-Personal/Tooling/Don-Limpio

val gradlew = if (isOsWindows()) "./gradlew.bat" else "./gradlew"

when (option) {
    CleaningOption.EXIT -> TODO()
    CleaningOption.UNDEFINED -> TODO()
    CleaningOption.GRADLE_UPGRADE -> runTasksForGradleUpdate()
    CleaningOption.AGP_UPGRADE -> TODO()
    CleaningOption.PROJECT_STRUCTURE_CHANGED -> TODO()
}








/** Helper functions */

fun runTasksForGradleUpdate() {
    Runtime.getRuntime().run {
        printInBold("⏳ Executing Gradle clean...")
        try {
            exec("$gradlew clean --quiet")
        } catch (e: IOException) {
            println(e.message)
            System.exit(1)
        }
        println()
    }
}

fun selectMRCleanTask(): CleaningOption {
    println("Hey there, here are the following options:")
    println("${CleaningOption.GRADLE_UPGRADE.value}. Gradle-related stuff")
    println("${CleaningOption.AGP_UPGRADE.value}. IDEA/Android Studio upgrade")
    println("${CleaningOption.PROJECT_STRUCTURE_CHANGED.value}. Remove old IDE caches ⚠")
    println("${CleaningOption.EXIT.value}. Cancel")

    val menuOption = "[1-6]".toRegex()
    val stdin : String? = readLine()

    return if (!menuOption.matches(stdin.toString())) {
        CleaningOption.UNDEFINED
    } else {
        CleaningOption.values()[stdin!!.toInt()]
    }
}

/*fun locateGradleHome(): File? {
    val envGradleHome = System.getenv("GRADLE_HOME")?.let { File(it) }
    val userGradleHome = File(userHome, ".gradle")

    return when {
        envGradleHome?.exists() == true -> envGradleHome
        userGradleHome.exists() -> userGradleHome
        else -> null
    }
}*/

fun printInBold(message: String) {
    if (isOsWindows()) {
        println(message)
    } else {
        println("\u001B[1;37m$message\u001B[0;37m")
    }
}

fun isOsWindows() = System.getProperty("os.name").startsWith("Windows", ignoreCase = true)

//fun Runtime.execOnWetRun(command: String) = if (wetRun) exec(command) else null

fun Process.printOutput(onlyErrors: Boolean = true) {
    if (onlyErrors.not()) {
        inputStream.bufferedReader().lines().forEach { println("     $it") }
    }
    errorStream.bufferedReader().lines().forEach { println("     $it") }
}


/** Helper components */
enum class CleaningOption(val value: Int) {
    UNDEFINED(0),
    GRADLE_UPGRADE(1),
    AGP_UPGRADE(2),
    PROJECT_STRUCTURE_CHANGED(3),
    EXIT(4),
}




/*
println("Script is running with ${args.size} args passed")

for(arg in args) {
    println("arg: $arg")
}*/
