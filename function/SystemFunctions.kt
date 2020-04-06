
/** Global System-related functions */

fun printInBold(message: String) {
    if (isOsWindows()) {
        println(message)
    } else {
        println("\u001B[1;37m$message\u001B[0;37m")
    }
}

fun isOsWindows() = System.getProperty("os.name").startsWith("Windows", ignoreCase = true)

fun Process.printOutput(onlyErrors: Boolean = true) {
    if (onlyErrors.not()) {
        inputStream.bufferedReader().lines().forEach { println("     $it") }
    }
    errorStream.bufferedReader().lines().forEach { println("     $it") }
}