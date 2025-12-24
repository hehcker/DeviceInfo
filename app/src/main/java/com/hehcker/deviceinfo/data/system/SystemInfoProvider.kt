package com.hehcker.deviceinfo.data.system

import android.content.Context
import android.os.Build
import android.os.SystemProperties

object SystemInfoProvider {
    fun get(context: Context): SystemInfo {
        return SystemInfo(
            androidVersion = Android.getAndroidVersionFromSdk(Build.VERSION.SDK_INT),
            codename = Android.getAndroidCodename(),
            firstApiLevel = Android
                .getAndroidVersionFromSdk(SystemProperties.getInt("ro.product.first_api_level", 0)),
            androidPatch = Android.formatSecurityPatch(Build.VERSION.SECURITY_PATCH),
            vendorPatch = Android.formatSecurityPatch(SystemProperties.get("ro.vendor.build.security_patch")),
            kernel = Linux.getFormattedKernelVersion(),
            build = Build.DISPLAY,
            buildType = Build.TYPE,
            buildDate = SystemProperties.get("ro.build.date"),
            fingerprint = Build.FINGERPRINT,
            systemFeatures = Android.getSystemFeatures(context),
            baseband = Build.getRadioVersion(),
            uptime = Android.getUptime(),
            ssuStatus = Android.getSSUStatus(),
            jvm = Android.getJVMVersion()
        )
    }
}