
/** Global IDEA-related functions */

fun runMiscFileCleanup(miscFile : File) {
    printInBold("🔥 Removing .idea/misc.xml file...")
    if (miscFile.exists()) {
        commonFileCleanupMessage(miscFile.delete())
    } else {
        println("${defaultVerboseIndentation}File not found. Skipped")
    }
    println()
}

fun runModulesFileCleanup(modulesFile: File) {
    printInBold("🔥 Removing .idea/modules.xml file...")
    if (modulesFile.exists()) {
        commonFileCleanupMessage(modulesFile.delete())
    } else {
        println("${defaultVerboseIndentation}File not found. Skipped")
    }
    println()
}

fun runWorkspaceFileCleanup(workspaceFile: File) {
    printInBold("🔥 Removing .idea/workspace.xml file...")
    if (workspaceFile.exists()) {
        commonFileCleanupMessage(workspaceFile.delete())
    } else {
        println("${defaultVerboseIndentation}File not found. Skipped")
    }
    println()
}

fun runJarRepositoriesFileCleanup(jarRepositoryFile: File) {
    printInBold("🔥 Removing .idea/jarRepositories.xml file...")
    if (jarRepositoryFile.exists()) {
        commonFileCleanupMessage(jarRepositoryFile.delete())
    } else {
        println("${defaultVerboseIndentation}File not found. Skipped")
    }
    println()
}

fun runIMLFilesCleanup(workingDirectory: File) {
    printInBold("🔥 Removing .iml files...")
    val content = workingDirectory.filteredContent(true) { file ->
        file.getExtension() == "iml"
    }
    content.deleteRecursively(wetRun = true, verbose = true)
    println()
}

fun runCachedFilesCleanup(workingDirectory: File) {
    printInBold("🔥 Removing .idea/caches folder...")
    workingDirectory.removeSubfoldersMatching { it.name.equals("caches", ignoreCase = true) }
    println()
}

fun runGradleLibraryFilesCleanup(workingDirectory: File) {
    printInBold("🔥 Removing .idea/libraries folder...")
    workingDirectory.removeSubfoldersMatching { it.name.equals("libraries", ignoreCase = true) }
    println()
}