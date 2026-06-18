package com.hehcker.deviceinfo.ui.screen.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.hehcker.deviceinfo.data.DataRepository
import com.hehcker.deviceinfo.data.display.DisplayInfo
import com.hehcker.deviceinfo.ui.component.list.addIfValid

data class DisplaySection<T>(
    val displayInfo: DisplayInfo,
    val items: List<T>,
)

class DisplayInfoViewModel(app: Application) : AndroidViewModel(app) {
    val displayInfos: List<DisplayInfo> = DataRepository.getDisplayInfo(app)

    val sections = displayInfos.mapIndexed { index, info ->
        val items = buildList {
            addIfValid("Type", info.type)
            addIfValid("Resolution", info.resolution)
            addIfValid("Ratio", info.ratio)
            addIfValid("Diagonal", info.diagonal)
            addIfValid("Size", info.size)
            addIfValid("Per Pixel Inch", info.ppi)
            addIfValid("Density", info.systemDensity)
            addIfValid("Refresh Rate", info.refreshRate)
            addIfValid("HDR Types", "${info.hdrTypes.size} available", isClickable = true)
            addIfValid("Available Modes", "${info.availableModes.size} available", isClickable = true)
            addIfValid("Wide Color Gamut", info.wideColorGamutStatus)
        }

        DisplaySection(
            displayInfo = info,
            items = items
        )
    }
}