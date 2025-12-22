package com.hehcker.deviceinfo.data.device

import android.os.Build
import com.hehcker.deviceinfo.R

object BrandProvider {
    fun getBrandIcon(): Int {
        val manufacturer = Build.MANUFACTURER.lowercase()
        val brand = Build.BRAND.lowercase()
        return when (manufacturer) {
            "xiaomi" -> return when (brand) {
                "redmi" -> R.drawable.ic_redmi
                "poco" -> R.drawable.ic_poco
                else -> R.drawable.ic_xiaomi
            }
            "samsung" -> R.drawable.ic_samsung
            "honor" -> R.drawable.ic_honor
            "huawei" -> R.drawable.ic_huawei
            "oneplus" -> R.drawable.ic_oneplus
            "realme" -> R.drawable.ic_realme
            "oppo" -> R.drawable.ic_oppo

            // to be filled
            else -> R.drawable.ic_android
        }
    }
}