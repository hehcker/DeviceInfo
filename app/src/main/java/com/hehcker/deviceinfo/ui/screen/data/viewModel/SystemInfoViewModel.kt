package com.hehcker.deviceinfo.ui.screen.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.hehcker.deviceinfo.data.DataRepository
import com.hehcker.deviceinfo.ui.component.list.addIfValid

class SystemInfoViewModel(app: Application) : AndroidViewModel(app) {

    val systemInfo = DataRepository.getSystemInfo(app)

    val uiItems = buildList {
        addIfValid("Codename", systemInfo.codename)
        addIfValid("First Android version", systemInfo.firstApiLevel)
        addIfValid("Android Security patch", systemInfo.androidPatch)
        addIfValid("Vendor Security patch", systemInfo.vendorPatch)
        addIfValid("Kernel version", systemInfo.kernel)
        addIfValid("Build", systemInfo.build)
        addIfValid("Build Type", systemInfo.buildType)
        addIfValid("Build Date", systemInfo.buildDate)
        addIfValid("Builder", systemInfo.builder)
        addIfValid("Fingerprint", systemInfo.fingerprint)
        addIfValid("Device Features", "${systemInfo.systemFeatures.size} available", isClickable = true)
        addIfValid("Baseband", systemInfo.baseband)
        addIfValid("Uptime", systemInfo.uptime)
        addIfValid("Seamless System Updates", systemInfo.ssuStatus)
        addIfValid("JVM", systemInfo.jvm)
    }
}