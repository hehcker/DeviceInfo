package com.hehcker.deviceinfo.ui.screen.data.viewModel

import android.app.Application
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import com.hehcker.deviceinfo.data.device.BrandProvider
import com.hehcker.deviceinfo.data.device.ModelProvider
import com.hehcker.deviceinfo.ui.component.InfoItem
import com.hehcker.deviceinfo.ui.component.addIfValid

class DeviceViewModel(application: Application) : AndroidViewModel(application) {
    val brandIconRes: Int = BrandProvider.getBrandIcon()
    private val marketingName: String? = ModelProvider.getMarketingName(application.applicationContext)

    val modelName: String = marketingName ?: Build.MODEL

    val deviceDetails: List<InfoItem> = buildList {
        addIfValid("Manufacturer", Build.MANUFACTURER)
        addIfValid("Brand", Build.BRAND)
        addIfValid("Model", Build.MODEL)
        addIfValid("Marketing Name", marketingName)
        addIfValid("Device", Build.DEVICE)
        addIfValid("Hardware", Build.HARDWARE)
    }
}