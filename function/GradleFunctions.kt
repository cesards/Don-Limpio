
/** Global Gradle-related functions */

fun runGradleClean(runtime: Runtime, gradleCommand: String) {
    printInBold("⏳ Executing Gradle clean...")
    try {
        if (verbose) println("${defaultVerboseIndentation}Running: $gradleCommand clean --quiet")
        runtime.exec("$gradleCommand clean --quiet").printOutput(onlyErrors = false)
        if (verbose) println("${defaultVerboseIndentation}Done ✔")
    } catch (e: IOException) {
        println(e.message)
        System.exit(1)
    }
    println()
}

fun runGradleStop(runtime: Runtime, gradleCommand: String) {
    printInBold("🔫 Killing Gradle daemon...")
    try {
        runtime.exec("$gradleCommand --stop").printOutput(onlyErrors = false)
    } catch (e: IOException) {
        println(e.message)
        System.exit(1)
    }
    println()
}

/*fun gradleHome(): File? {
    // ? WHY ? val envGradleHome = System.getenv("GRADLE_HOME")?.let { File(it) }
    val userGradleHome = File(userHome, ".gradle")

    println("user gradle home: $userGradleHome")

    return when {
        //envGradleHome?.exists() == true -> envGradleHome
        userGradleHome.exists() -> userGradleHome
        else -> null
    }
}



fun runUserLevelGradleCacheRemoval(runtime: Runtime) {
    gradleHome?.run {
        printInBold("🔥 Removing user-level Gradle caches folder...")
        println("$path")

        // IT Doesn't work, but if I run it from a Kotlin scratch, IT DOES
        // SCRIPT RANDOM FUCKING WORKS.
        Runtime.getRuntime().exec(arrayOf("sh", "-c", "rm -rf ~/.gradle/caches"))

        //runtime.exec(arrayOf("sh", "-c", "rm -rf ~/.gradle/caches"))

        println(runtime.exec("pwd"))

        //val gradleCacheFolder = File("$path/caches/")
        *//*if (gradleCacheFolder.exists()) {
            try {
                println("Deleting caches file in home directory")
                //runtime.exec("rm -rf ${gradleCacheFolder.absolutePath}")
                //runtime.exec(arrayOf("sh", "-c", "rm -rf ~/.gradle/caches"))

            } catch (e: IOException) {
                println("error deleting: ${e.message}")
            }
        }*//*
        //println("${gradleCacheFolder.path}")
        //gradleCacheFolder.deleteRecursively()
        //removeSubfoldersMatching { it.name.equals("caches", ignoreCase = true) }
        println()
    }
}*/

/*
fun runProjectLevelGradleGeneratedFolderRemoval() {
    printInBold("🔥 Removing '.gradle' folder...")
    workingDir.removeSubfoldersMatching { it.name.equals(".gradle", ignoreCase = true) }
    println()
}*/
