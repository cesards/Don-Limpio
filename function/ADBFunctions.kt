
/** Global ADB-related functions */

fun Runtime.runADBMatanza() {
    printInBold("\uD83D\uDD2B Killing ADB server...")
    try {
        exec("adb kill-server").printOutput(onlyErrors = false)
        // execOnWetRun("")?.printIfNoError("Adb server killed.")
        exec("killall adb").printOutput(onlyErrors = false)
    } catch (e: IOException) {
        println(e.message)
        System.exit(1)
    }
    println()
}