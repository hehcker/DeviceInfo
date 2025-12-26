package com.hehcker.deviceinfo.ui.screen.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.hehcker.deviceinfo.data.display.DisplayInfoProvider
import com.hehcker.deviceinfo.ui.component.addIfValid

class DisplayInfoViewModel(app: Application) : AndroidViewModel(app) {
    val displayInfo = DisplayInfoProvider.get(app)

    val uiItems = buildList {
        addIfValid("Resolution", displayInfo.resolution)
        addIfValid("Ratio", displayInfo.ratio)
        addIfValid("Diagonal", displayInfo.diagonal)
        addIfValid("Size", displayInfo.size)
        addIfValid("Per Pixel Inch", displayInfo.ppi)
        addIfValid("Density", displayInfo.systemDensity)
        addIfValid("Refresh Rate", displayInfo.refreshRate)
        addIfValid("HDR Types", "${displayInfo.hdrTypes.size} available")
        addIfValid("Available Modes", "${displayInfo.availableModes.size} available")
        addIfValid("Wide Color Gamut", displayInfo.wideColorGamutStatus)
    }
}