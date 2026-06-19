package com.hehcker.deviceinfo.data.battery

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build

object BatteryInfoProvider {
    fun get(context: Context): BatteryInfo {
        val intent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager

        val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, 100) ?: 100
        val levelPercent = if (scale > 0) (level * 100 / scale) else -1

        val cycleCount = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER).let {
                intent?.getIntExtra("android.os.extra.CYCLE_COUNT", -1) ?: -1
            }
        } else -1

        return BatteryInfo(
            level = levelPercent,
            health = intent?.getIntExtra(BatteryManager.EXTRA_HEALTH, -1) ?: -1,
            status = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1,
            powerSource = intent?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) ?: -1,
            technology = intent?.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY),
            temperature = intent?.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) ?: -1,
            voltage = intent?.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1) ?: -1,
            currentNow = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW),
            chargeCounter = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER),
            cycleCount = cycleCount
        )
    }
}