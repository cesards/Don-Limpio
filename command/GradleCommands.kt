import java.io.File

fun runCleanupForGradleUpdate() {
    // // assert(userHome.exists()) { "Unable to determine the user home folder, aborting..." } ???

    Runtime.getRuntime().run {
        /** Initializations */

        val workingDir : File = File(Paths.get("").toAbsolutePath().toString())
        val gradleCommand : String = if (isOsWindows()) "./gradlew.bat" else "./gradlew"
        val gradleHiddenFolder =  File("${workingDir.absolutePath}/.gradle")

        /** Running preconditions */

        val gradleCommandFile = if (isOsWindows()) "gradlew.bat" else "gradlew"
        if (!gradleHiddenFolder.exists() || !File("${workingDir.absolutePath}/$gradleCommandFile").exists()) {
            println("A small check was run and this doesn't seem to be a Gradle-based project ┏༼ ◉ ╭╮ ◉༽┓")
            println("")
            println("Please, make sure you are running Don Limpio in one of your Gradle-based projects. If that was " +
                    "the case, this project is Open-Source. " +
                    "Please report the bug here -> https://github.com/cesards/Don-Limpio")
            System.exit(1)
        }

        /** Running tasks */

        runGradleClean(this, gradleCommand)
        runGradleStop(this, gradleCommand)

        // Before killing ADB server, we need to make sure the user has ADB setup:
        // command -v adb
        // We might not need the following.
        //runADBMatanza(this)

        printInBold("🔥 Removing every '.gradle' folder...")
        println(File("${workingDir.absolutePath}/.gradle").absolutePath)
        gradleHiddenFolder.deleteRecursively()

        println()

        //runUserLevelGradleCacheRemoval(this)
        //runProjectLevelGradleGeneratedFolderRemoval()
    }
}
