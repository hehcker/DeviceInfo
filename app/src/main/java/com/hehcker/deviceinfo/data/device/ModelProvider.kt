package com.hehcker.deviceinfo.data.device

import android.content.Context
import android.os.Build
import android.os.SystemProperties
import android.provider.Settings

object ModelProvider {

    fun getMarketingName(context: Context): String? {
        val brand = Build.BRAND.lowercase()

        val brandMarketingName = when (brand) {
            "xiaomi", "redmi", "poco" -> getXiaomiName()
            "samsung" -> getSamsungName(context)
            "sony" -> getSonyName()
            "huawei", "honor" -> getHuaweiName()
            "oneplus", "realme", "oppo" -> getOplusName()
            "vivo" -> getVivoName()

            // to be filled
            else -> null
        }

        val marketingName = brandMarketingName ?: getGenericFallback()

        return marketingName?.takeIf { !marketingName.equals(Build.MODEL, ignoreCase = true) }
    }

    private fun getGenericFallback(): String? {

        val genericProperties = listOf(
            "ro.product.marketname",
            "ro.product.display",
            "ro.display.series",
            "ro.config.marketing_name",
            "ro.config.devicename"
        )

        for (property in genericProperties) {
            val name = SystemProperties.get(property)
            if (name != null) return name
        }
        return null
    }

    private fun getXiaomiName(): String? {
        return SystemProperties.get("ro.product.marketname")
            .takeIf { it.isNotBlank() }
    }
    private fun getSonyName(): String? {
        return SystemProperties.get("ro.semc.product.name")
            .takeIf { it.isNotBlank() }
    }

    private fun getHuaweiName(): String? {
        return SystemProperties.get("ro.config.marketing_name")
            .takeIf { it.isNotBlank() }
    }

    private fun getOplusName(): String? {
        return SystemProperties.get("ro.vendor.oplus.market.name")
            .takeIf { it.isNotBlank() }
    }

    private fun getVivoName(): String? {
        return SystemProperties.get("ro.vivo.camera.watermark")
            .takeIf { it.isNotBlank() } ?:
            SystemProperties.get("ro.vivo.market.name")
            .takeIf { it.isNotBlank() } ?:
            SystemProperties.get("ro.vivo.product.release.name")
            .takeIf { it.isNotBlank() }

    }

    private fun getSamsungName(context: Context): String? {
        return Settings.Global.getString(context.contentResolver, "default_device_name")
            .takeIf { !it.isNullOrBlank() }
    }
}