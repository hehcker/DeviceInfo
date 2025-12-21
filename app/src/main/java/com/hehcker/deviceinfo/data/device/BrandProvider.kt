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

                // to be filled
                else -> R.drawable.ic_xiaomi
            }
            else -> R.drawable.ic_android
        }
    }
}