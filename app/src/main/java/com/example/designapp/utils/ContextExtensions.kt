package com.example.designapp.utils

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

fun Context.openSkype(number: String? = null) {
    if (!isSkypeClientInstalled()) {
        goToMarket()
        return
    }

    val skypeUri = number?.let { Uri.parse("skype:$it") }
    val myIntent = Intent(Intent.ACTION_VIEW, skypeUri)

    myIntent.component = ComponentName("com.skype.raider", "com.skype4life.MainActivity")
    myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

    startActivity(myIntent)
}

fun Context.isSkypeClientInstalled(): Boolean {
    try {
        packageManager.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES)
    } catch (e: PackageManager.NameNotFoundException) {
        return false
    }
    return true
}

fun Context.goToMarket() {
    val marketUri = Uri.parse("market://details?id=com.skype.raider")
    val myIntent = Intent(Intent.ACTION_VIEW, marketUri)
    myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(myIntent)
}

