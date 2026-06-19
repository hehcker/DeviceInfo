package com.hehcker.deviceinfo.data.battery

import android.os.BatteryManager

object Battery {
    fun formatLevel(level: Int) =
        if (level >= 0) "$level%" else null

    fun formatTemperature(temp: Int) =
        if (temp >= 0) "${temp / 10.0} °C" else null

    fun formatVoltage(voltage: Int) =
        if (voltage >= 0) "$voltage mV" else null

    fun formatCurrentNow(currentNow: Long): String? =
        when {
            currentNow < 0L -> "+${-currentNow / 1000} mA"
            currentNow > 0L -> "-${currentNow / 1000} mA"
            else -> null
        }

    fun formatChargeCounter(chargeCounter: Long) =
        if (chargeCounter >= 0) "${chargeCounter / 1000} mAh" else null

    fun formatCycleCount(cycleCount: Int) =
        if (cycleCount >= 0) cycleCount.toString() else null

    fun formatHealthLabel(health: Int) = when (health) {
        BatteryManager.BATTERY_HEALTH_GOOD -> "Good"
        BatteryManager.BATTERY_HEALTH_OVERHEAT -> "Overheat"
        BatteryManager.BATTERY_HEALTH_DEAD -> "Dead"
        BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "Over Voltage"
        BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "Failure"
        BatteryManager.BATTERY_HEALTH_COLD -> "Cold"
        else -> "Unknown"
    }

    fun formatStatusLabel(status: Int) = when (status) {
        BatteryManager.BATTERY_STATUS_CHARGING -> "Charging"
        BatteryManager.BATTERY_STATUS_DISCHARGING -> "Discharging"
        BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "Not Charging"
        BatteryManager.BATTERY_STATUS_FULL -> "Full"
        else -> "Unknown"
    }

    fun formatPowerSourceLabel(plugged: Int) = when (plugged) {
        BatteryManager.BATTERY_PLUGGED_AC -> "AC"
        BatteryManager.BATTERY_PLUGGED_USB -> "USB"
        BatteryManager.BATTERY_PLUGGED_WIRELESS -> "Wireless"
        BatteryManager.BATTERY_PLUGGED_DOCK -> "Dock"
        0 -> "Unplugged"
        else -> "Unknown"
    }
}