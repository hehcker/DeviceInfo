package com.hehcker.deviceinfo

import android.app.Application
import android.content.Context
import org.lsposed.hiddenapibypass.HiddenApiBypass

class DeviceInfoApplication :  Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        runCatching {
            HiddenApiBypass.addHiddenApiExemptions("Landroid/view/Display;")
        }
    }
}