package com.hehcker.deviceinfo.data.device

import kotlinx.serialization.Serializable

@Serializable
data class DeviceInfo(
    val manufacturer: String,
    val brand: String,
    val model: String,
    val marketingName: String?,
    val device: String,
    val hardware: String
)
