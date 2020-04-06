
/** Global File-related functions */

fun commonFileCleanupMessage(fileDeleted: Boolean) {
    if (fileDeleted) {
        println("${defaultVerboseIndentation}Done ✔️")
    } else {
        println("${defaultVerboseIndentation}Failed ❌")
    }
}

fun File.getExtension(): String {
    val fileName: String = getName()
    return if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
        fileName.substring(fileName.lastIndexOf(".") + 1)
    } else {
        ""
    }
}

fun File.removeSubfoldersMatching(matcher: (file: File) -> Boolean) {
    //val matchingDirectories = filteredContent(recursively = recursively) {
    val matchingDirectories = filteredContent(recursively = true) {
        it.isDirectory && matcher(it)
    }
    matchingDirectories.deleteRecursively(verbose = true, wetRun = true)

    // if (backup) {
    //     matchingDirectories.backupAndDeleteByRenaming()
    // } else {
    //     matchingDirectories.deleteRecursively()
    // }
}

fun File.filteredContent(recursively: Boolean, matcher: (File) -> Boolean): Sequence<File> =
        listFiles()
                .asSequence()
                .flatMap {
                    when {
                        matcher(it) -> sequenceOf(it)
                        recursively && it.isDirectory -> {
                            it.filteredContent(recursively = true, matcher = matcher)
                        }
                        else -> sequenceOf()
                    }
                }

fun Sequence<File>.deleteRecursively(verbose : Boolean = false, wetRun : Boolean = false) =
        onEach { if (verbose) println("${defaultVerboseIndentation}Deleted: ${it.absolutePath}") }
                .forEach { if (wetRun) it.deleteRecursively() }