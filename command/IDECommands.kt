
fun runCleanupForIDEUpdate() {
    // // assert(userHome.exists()) { "Unable to determine the user home folder, aborting..." } ???

    Runtime.getRuntime().run {
        /** Initializations */

        val workingDir : File = File(Paths.get("").toAbsolutePath().toString())
        val gradleCommand : String = if (isOsWindows()) "./gradlew.bat" else "./gradlew"
        val ideaHiddenFolder =  File("${workingDir.absolutePath}/.idea")

        /** Running preconditions */

        if (!ideaHiddenFolder.exists()) {
            println("A small check was run and this doesn't seem to be an IDEA / AS project ┏༼ ◉ ╭╮ ◉༽┓")
            println("")
            println("Please, make sure you are running Don Limpio in one on a JetBrains IDE. If that was " +
                    "the case, this project is Open-Source. " +
                    "Please report the bug here -> https://github.com/cesards/Don-Limpio")
            System.exit(1)
        }

        /** Running tasks */

        runGradleClean(this, gradleCommand)
        runGradleStop(this, gradleCommand)

        runMiscFileCleanup(File("${ideaHiddenFolder}/misc.xml"))
        runModulesFileCleanup(File("${ideaHiddenFolder}/modules.xml"))
        runWorkspaceFileCleanup(File("${ideaHiddenFolder}/workspace.xml"))
        runJarRepositoriesFileCleanup(File("${ideaHiddenFolder}/jarRepositories.xml"))
        runIMLFilesCleanup(workingDir)
        runCachedFilesCleanup(ideaHiddenFolder)
        runGradleLibraryFilesCleanup(ideaHiddenFolder)
        // gradle.xml ?
        // encodings.xml ?
        // compiler.xml ?
        //runUserLevelGradleCacheRemoval(this)
        //runProjectLevelGradleGeneratedFolderRemoval()

        printInBold("  .▀█▀.█▄█.█▀█.█▄.█.█▄▀　█▄█.█▀█.█─█\n" +
                    "  ─.█.─█▀█.█▀█.█.▀█.█▀▄　─█.─█▄█.█▄█")
        println()
        printInBold("Now it's time to close the IDE project's window and reopen it again :) Hopefully your problems will be gone.")
        println()
    }
}
