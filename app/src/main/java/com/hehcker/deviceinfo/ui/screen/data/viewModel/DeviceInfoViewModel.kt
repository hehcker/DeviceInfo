package com.hehcker.deviceinfo.ui.screen.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.hehcker.deviceinfo.data.device.DeviceInfoProvider
import com.hehcker.deviceinfo.ui.component.addIfValid

class DeviceInfoViewModel(application: Application) : AndroidViewModel(application) {
    val deviceInfo = DeviceInfoProvider.get(application)

    val uiItems = buildList {
        addIfValid("Manufacturer", deviceInfo.manufacturer)
        addIfValid("Brand", deviceInfo.brand)
        addIfValid("Model", deviceInfo.model)
        addIfValid("Marketing Name", deviceInfo.marketingName)
        addIfValid("Device", deviceInfo.device)
        addIfValid("Hardware", deviceInfo.hardware)
    }
}