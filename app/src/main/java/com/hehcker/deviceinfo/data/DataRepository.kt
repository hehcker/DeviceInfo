package com.hehcker.deviceinfo.data

import android.content.Context
import com.hehcker.deviceinfo.data.device.DeviceInfo
import com.hehcker.deviceinfo.data.device.DeviceInfoProvider
import com.hehcker.deviceinfo.data.display.DisplayInfo
import com.hehcker.deviceinfo.data.display.DisplayInfoProvider
import com.hehcker.deviceinfo.data.system.SystemInfo
import com.hehcker.deviceinfo.data.system.SystemInfoProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json

object DataRepository {
    private val _importedDump = MutableStateFlow<DeviceDump?>(null)
    val importedDump: StateFlow<DeviceDump?> = _importedDump.asStateFlow()

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    fun getDeviceInfo(context: Context): DeviceInfo {
        return _importedDump.value?.deviceInfo ?: DeviceInfoProvider.get(context)
    }

    fun getDisplayInfo(context: Context): List<DisplayInfo> {
        return _importedDump.value?.displayInfos ?: DisplayInfoProvider.getAll(context)
    }

    fun getSystemInfo(context: Context): SystemInfo {
        return _importedDump.value?.systemInfo ?: SystemInfoProvider.get(context)
    }

    fun importDump(jsonString: String): Boolean {
        return try {
            val parsed = json.decodeFromString<DeviceDump>(jsonString)
            _importedDump.value = parsed
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun generateDump(context: Context): String {
        val dump = DeviceDump(
            deviceInfo = DeviceInfoProvider.get(context),
            systemInfo = SystemInfoProvider.get(context),
            displayInfos = DisplayInfoProvider.getAll(context)
        )
        return json.encodeToString(dump)
    }

    fun forgetDump() {
        _importedDump.value = null
    }
}