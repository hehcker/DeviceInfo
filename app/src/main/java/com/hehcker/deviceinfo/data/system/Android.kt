package com.hehcker.deviceinfo.data.system

import android.os.Build
import android.os.SystemClock
import android.os.SystemProperties
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

object Android {
    private val sdkToVersion = mapOf(
        30 to "11",
        31 to "12",
        32 to "12.1",
        33 to "13",
        34 to "14",
        35 to "15",
        36 to "16",
        //37 to "17"
    )

    fun getAndroidVersionFromSdk(sdk: Int): String =
        sdkToVersion[sdk] ?: Build.VERSION.RELEASE

    fun getAndroidCodename(): String {
        return when (Build.VERSION.SDK_INT) {
            Build.VERSION_CODES.R -> "Red Velvet Cake"
            Build.VERSION_CODES.S -> "Snow Cone"
            Build.VERSION_CODES.S_V2 -> "Snow Cone V2"
            Build.VERSION_CODES.TIRAMISU -> "Tiramisu"
            Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> "Upside Down Cake"
            Build.VERSION_CODES.VANILLA_ICE_CREAM -> "Vanilla Ice Cream"
            Build.VERSION_CODES.BAKLAVA -> "Baklava"

            else -> Build.VERSION.CODENAME
        }
    }

    fun formatSecurityPatch(patch: String?): String? =
        runCatching {
            patch?.let {
                val date = LocalDate.parse(it)
                date.format(
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                        .withLocale(Locale.getDefault())
                )
            }
        }.getOrNull()


    fun getUptime(): String {
        val seconds = SystemClock.elapsedRealtime() / 1000
        val days = seconds / 86400
        val hours = (seconds % 86400) / 3600
        val minutes = (seconds % 3600) / 60

        return "$days days %02d:%02d".format(hours, minutes)
    }


    // ref: https://github.com/kevintresuelo/treble/blob/master/app/src/main/java/com/kevintresuelo/treble/checker/AB.kt
    fun getSSUStatus(): String {
        /**
         * Checks if the device supports Virtual A/B partition
         */
        if (SystemProperties.get("ro.virtual_ab.enabled") == "true" && SystemProperties.get("ro.virtual_ab.retrofit") == "false") {
            return "Supported (Virtual A/B)"
        }

        /**
         * Checks if the device supports the conventional A/B partition
         */
        if (!SystemProperties.get("ro.boot.slot_suffix").isNullOrBlank() || SystemProperties.get("ro.build.ab_update") == "true") {
            return "Supported (Physical A/B)"
        }

        /**
         * Returns null if the device doesn't support A/B partitions at all
         */
        return "Unsupported"
    }

    fun getJVMVersion(): String? =
        System.getProperty("java.vm.version")?.let { "ART $it" }
}