package com.hehcker.deviceinfo.data

import com.hehcker.deviceinfo.data.device.DeviceInfo
import com.hehcker.deviceinfo.data.display.DisplayInfo
import com.hehcker.deviceinfo.data.system.SystemInfo
import kotlinx.serialization.Serializable

@Serializable
data class DeviceDump(
    val timestamp: Long = System.currentTimeMillis(),
    val deviceInfo: DeviceInfo,
    val systemInfo: SystemInfo,
    val displayInfos: List<DisplayInfo>
)