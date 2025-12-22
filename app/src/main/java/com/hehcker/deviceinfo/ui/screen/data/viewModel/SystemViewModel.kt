package com.hehcker.deviceinfo.ui.screen.data.viewModel

import android.app.Application
import android.os.Build
import android.os.SystemProperties
import androidx.lifecycle.AndroidViewModel
import com.hehcker.deviceinfo.data.system.SystemProvider
import com.hehcker.deviceinfo.ui.component.InfoItem
import com.hehcker.deviceinfo.ui.component.addIfValid

class SystemViewModel(application: Application) : AndroidViewModel(application) {
    val androidVersion = SystemProvider.getAndroidVersionFromSdk(Build.VERSION.SDK_INT)
    private val firstAndroidVersion = SystemProvider.getAndroidVersionFromSdk(SystemProperties.getInt("ro.product.first_api_level", 0))
    private val vendorSecurityPatch = SystemProvider.formatSecurityPatch(SystemProperties.get("ro.vendor.build.security_patch"))
    private val androidSecurityPatch = SystemProvider.formatSecurityPatch(Build.VERSION.SECURITY_PATCH)
    private val buildDate = SystemProperties.get("ro.build.date")

    val systemDetails: List<InfoItem> = buildList {
        addIfValid("Codename", SystemProvider.getAndroidCodename())
        addIfValid("First android version", firstAndroidVersion)
        addIfValid("Android Security patch", androidSecurityPatch)
        addIfValid("Vendor Security patch", vendorSecurityPatch)
        addIfValid("Kernel version", SystemProvider.getFormattedKernelVersion())
        addIfValid("Build", Build.DISPLAY)
        addIfValid("Build Type", Build.TYPE)
        addIfValid("Build Date", buildDate)
        addIfValid("Builder", "${Build.USER}@${Build.HOST}")
        addIfValid("Fingerprint", Build.FINGERPRINT)
        addIfValid("Baseband", Build.getRadioVersion())
        addIfValid("Uptime", SystemProvider.getUptime())
        addIfValid("Seamless System Updates", SystemProvider.getSSUStatus())
        addIfValid("JVM", SystemProvider.getJVMVersion())
    }
}