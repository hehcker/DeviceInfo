package com.hehcker.deviceinfo.data.system

import android.os.Build
import android.os.SystemClock
import android.os.SystemProperties
import android.system.Os
import android.system.StructUtsname
import android.text.format.DateFormat
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.regex.Matcher
import java.util.regex.Pattern


object SystemProvider {
    fun getAndroidVersionFromSdk(sdk: Int): String {
        return when (sdk) {
            30 -> "11"
            31 -> "12"
            32 -> "12.1"
            33 -> "13"
            34 -> "14"
            35 -> "15"
            36 -> "16"
            else -> Build.VERSION.RELEASE
        }
    }

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

    fun formatSecurityPatch(patch: String?): String? {
        val locale = Locale.getDefault()
        if (!patch.isNullOrEmpty()) {
            try {
                val template = SimpleDateFormat("yyyy-MM-dd")
                val patchDate = template.parse(patch)
                val format: String? = DateFormat.getBestDateTimePattern(locale, "dMMMMyyyy")
                return DateFormat.format(format, patchDate).toString()
            } catch (_: ParseException) {
                // broken parse; fall through and use the raw string
            }
        }
        return null
    }

    fun getFormattedKernelVersion(): String {
        return formatKernelVersion(Os.uname())
    }

    private fun formatKernelVersion(uname: StructUtsname): String {
        // Example:
        // 4.9.29-g958411d
        // #1 SMP PREEMPT Wed Jun 7 00:06:03 CST 2017
        val VERSION_REGEX =
            "(#\\d+) " +  /* group 1: "#1" */
            "(?:.*?)?" +  /* ignore: optional SMP, PREEMPT, and any CONFIG_FLAGS */
            "((Sun|Mon|Tue|Wed|Thu|Fri|Sat).+)" /* group 2: "Thu Jun 28 11:02:39 PDT 2012" */
        val m: Matcher = Pattern.compile(VERSION_REGEX).matcher(uname.version)
        if (!m.matches()) {
            Log.e("getKernelVersion", "Regex did not match on uname version " + uname.version)
            return "Unavailable"
        }

        // Example output:
        // 4.9.29-g958411d
        // #1 Wed Jun 7 00:06:03 CST 2017
        return StringBuilder().append(uname.release)
            .append("\n")
            .append(m.group(1))
            .append(" ")
            .append(m.group(2)).toString()
    }

    private fun formatTwoDigitNumber(i2: Int): String {
        if (i2 >= 10) {
            return i2.toString()
        }
        return "0$i2"
    }

    fun getUptime(): String {
        val elapsedTimeInSeconds = SystemClock.elapsedRealtime() / 1000
        val uptimeInSeconds = if (elapsedTimeInSeconds == 0L) SystemClock.uptimeMillis() / 1000 else elapsedTimeInSeconds

        val totalHours = (uptimeInSeconds / 3600).toInt()
        val totalDays = totalHours / 24
        val remainingHours = totalHours % 24
        val remainingMinutes = ((uptimeInSeconds / 60) % 60).toInt()

        return "$totalDays days ${formatTwoDigitNumber(remainingHours)}:${formatTwoDigitNumber(remainingMinutes)}"
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

    fun getJVMVersion(): String? {
        return System.getProperty("java.vm.version")?.let { "ART $it" }
    }
}