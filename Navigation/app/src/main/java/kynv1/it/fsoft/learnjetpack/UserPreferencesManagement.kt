package kynv1.it.fsoft.learnjetpack

import android.content.Context
import androidx.core.content.edit

private const val PRE_NAME = "kynv1.it.fsoft.learnjetpack.shared_preferences"
private const val USERNAME_KEY = "username_key"
private const val PASSWORD_KEY = "password_key"
private const val LOGGED_IN_KEY = "logged_in_key"

fun saveUsernamePassword(context: Context, username: String, password: String) {
    val sharePref = context.getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE)
    sharePref.edit(commit = false) {
        putString(USERNAME_KEY, username)
        putString(PASSWORD_KEY, password)
    }
}

fun saveLoginStatus(context: Context, loggedIn: Boolean) {
    val sharePref = context.getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE)
    sharePref.edit(commit = false) {
        putBoolean(LOGGED_IN_KEY, loggedIn)
    }
}

fun getUsername(context: Context):String?{
    val sharePref = context.getSharedPreferences(PRE_NAME,Context.MODE_PRIVATE)
    return sharePref.getString(USERNAME_KEY,"")
}

fun getPassword(context: Context):String?{
    val sharePref = context.getSharedPreferences(PRE_NAME,Context.MODE_PRIVATE)
    return sharePref.getString(PASSWORD_KEY,"")
}

fun getLoginStatus(context: Context):Boolean{
    val sharePref = context.getSharedPreferences(PRE_NAME,Context.MODE_PRIVATE)
    return sharePref.getBoolean(LOGGED_IN_KEY,false)
}