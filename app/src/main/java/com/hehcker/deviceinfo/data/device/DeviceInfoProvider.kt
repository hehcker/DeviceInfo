package com.hehcker.deviceinfo.data.device

import android.content.Context
import android.os.Build

object DeviceInfoProvider {
    fun get(context: Context): DeviceInfo {
        val marketingName = Model.getMarketingName(context)

        return DeviceInfo(
            manufacturer = Build.MANUFACTURER,
            brand = Build.BRAND,
            model = Build.MODEL,
            marketingName = marketingName,
            device = Build.DEVICE,
            hardware = Build.HARDWARE,
            brandIconRes = Brand.getBrandIcon()
        )
    }
}