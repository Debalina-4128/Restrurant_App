package com.example.restrurant_app.utils

import android.content.Context
import android.os.Build
import java.util.Locale

fun Context.updateLocale(locale: Locale): Context {
    val config = resources.configuration
    val newLocale = locale
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        config.setLocale(newLocale)
        createConfigurationContext(config)
    } else {
        @Suppress("DEPRECATION")
        config.locale = newLocale
        @Suppress("DEPRECATION")
        resources.updateConfiguration(config, resources.displayMetrics)
        this
    }
}