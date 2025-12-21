package com.hehcker.deviceinfo.data.device

import android.content.Context
import android.os.Build
import android.os.SystemProperties
import android.provider.Settings

object ModelProvider {

    fun getMarketingName(context: Context): String? {
        val manufacturer = Build.MANUFACTURER.lowercase()

        return when (manufacturer) {
            "xiaomi", "redmi", "poco" -> getXiaomiName()
            "samsung" -> getSamsungName(context)

            // to be filled
            else -> null
        }
    }

    private fun getXiaomiName(): String? {
        return SystemProperties.get("ro.product.marketname")
            .takeIf { it.isNotBlank() }
    }

    private fun getSamsungName(context: Context): String? {
        return Settings.Global.getString(context.contentResolver, "default_device_name")
            .takeIf { !it.isNullOrBlank() }
    }
}