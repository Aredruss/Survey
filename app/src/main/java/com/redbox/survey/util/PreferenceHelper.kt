package com.redbox.survey.util

import android.app.Activity
import android.content.Context

object PreferenceHelper {
    fun setUserData(context: Context, name: String, platoon: String) {
        val sharedPref =
            (context as Activity).getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("name", name)
            putString("platoon", platoon)
            commit()
        }
    }

    fun getUserData(context: Context): Pair<String, String> {
        val sharedPref =
            (context as Activity).getPreferences(Context.MODE_PRIVATE)
        return Pair<String, String>(
            sharedPref.getString("name", "NO_NAME"),
            sharedPref.getString("platoon", "NO_PLATOON")
        )
    }

    fun checkIfLogged(context: Context): Boolean {
        val sharedPref =
            (context as Activity).getPreferences(Context.MODE_PRIVATE)
        return sharedPref.contains("name") && sharedPref.contains("platoon")
    }
}