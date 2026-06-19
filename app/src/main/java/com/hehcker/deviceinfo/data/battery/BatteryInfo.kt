package com.hehcker.deviceinfo.data.battery

import kotlinx.serialization.Serializable

@Serializable
data class BatteryInfo(
    val level: Int = -1,
    val health: Int = -1,
    val status: Int = -1,
    val powerSource: Int = -1,
    val technology: String? = null,
    val temperature: Int = -1,
    val voltage: Int = -1,
    val currentNow: Long = 0,
    val chargeCounter: Long = -1L,
    val cycleCount: Int = -1,
)
