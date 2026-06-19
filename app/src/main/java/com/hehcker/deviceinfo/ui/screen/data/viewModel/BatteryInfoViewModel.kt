package com.hehcker.deviceinfo.ui.screen.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hehcker.deviceinfo.data.DataRepository
import com.hehcker.deviceinfo.data.battery.Battery
import com.hehcker.deviceinfo.data.battery.BatteryInfo
import com.hehcker.deviceinfo.ui.component.list.UiItem
import com.hehcker.deviceinfo.ui.component.list.addIfValid
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BatteryInfoViewModel(application: Application) : AndroidViewModel(application) {

    private val _batteryInfo = MutableStateFlow(DataRepository.getBatteryInfo(application))
    val batteryInfo: StateFlow<BatteryInfo> = _batteryInfo.asStateFlow()

    private val _uiItems = MutableStateFlow(buildUiItems(_batteryInfo.value))
    val uiItems: StateFlow<List<UiItem>> = _uiItems.asStateFlow()

    init {
        viewModelScope.launch {
            while (true) {
                delay(1_000)
                val info = DataRepository.getBatteryInfo(application)
                _batteryInfo.value = info
                _uiItems.value = buildUiItems(info)
            }
        }
    }

    private fun buildUiItems(info: BatteryInfo) = buildList {
        addIfValid("Level",        Battery.formatLevel(info.level))
        addIfValid("Status",       Battery.formatStatusLabel(info.status))
        addIfValid("Health",       Battery.formatHealthLabel(info.health))
        addIfValid("Cycle Count",  Battery.formatCycleCount(info.cycleCount))
        addIfValid("Current Now",  Battery.formatCurrentNow(info.currentNow))
        addIfValid("Charge Counter", Battery.formatChargeCounter(info.chargeCounter))
        addIfValid("Power Source", Battery.formatPowerSourceLabel(info.powerSource))
        addIfValid("Temperature",  Battery.formatTemperature(info.temperature))
        addIfValid("Voltage",      Battery.formatVoltage(info.voltage))
        addIfValid("Technology",   info.technology)
    }
}