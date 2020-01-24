package com.szakdolgozat.mygrades.internetconnection

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.provider.Settings
import com.szakdolgozat.mygrades.ui.splash.SplashActivity
import java.lang.Exception

object InternetConnectionChecker {

    private fun hasInternetConnection(activity: Activity) :Boolean
    {
        val conManager: ConnectivityManager= activity
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val hasConnection= (conManager.activeNetworkInfo!=null && conManager.activeNetworkInfo.isConnected)
        return hasConnection
    }

    fun internetIsAvailable(activity: Activity): Boolean{

        if(hasInternetConnection(activity)) {

            val runtime = Runtime.getRuntime()
            try {
                val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
                val exitValue = ipProcess.waitFor();
                return (exitValue == 0)

            } catch (e: Exception) {
            }

        }

        return false;
        }

}