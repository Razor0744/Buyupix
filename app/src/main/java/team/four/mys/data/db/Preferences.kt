package team.four.mys.data.db

import android.content.Context
import android.content.SharedPreferences

object Preferences {
    private const val NAME = "Settings"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context){
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    fun getSettings(key: String): String {
        return preferences.getString(key, "null").toString()
    }

    fun setSettings(key: String, value: String){
        preferences.edit().putString(key, value).apply()
    }
}