package ca.nkrishnaswamy.virtualcardcollectionbinder.network

import android.content.Context
import java.io.IOException

object InternetChecker {
    fun checkInternetConnection(): Boolean {
        val runtime: Runtime = Runtime.getRuntime()
        try {
            val ipProcess: Process = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
            val exitValue: Int = ipProcess.waitFor()
            if (exitValue == 0) {
                return true
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return false
    }
}