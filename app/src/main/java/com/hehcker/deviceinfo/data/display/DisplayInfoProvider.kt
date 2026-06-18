package com.hehcker.deviceinfo.data.display

import android.content.Context
import android.hardware.display.DisplayManager
import android.view.Display as AndroidDisplay

object DisplayInfoProvider {
    fun getAll(context: Context): List<DisplayInfo> {
        val displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager

        return displayManager.displays
            .sortedBy { it.displayId }
            .mapIndexedNotNull { index, display ->
                runCatching { get(context, display, index) }.getOrNull()
            }
    }

    private fun get(context: Context, display: AndroidDisplay, index: Int): DisplayInfo {
        val displayContext = context.createDisplayContext(display)

        return DisplayInfo(
            name = Display.getDisplayName(display),
            type = Display.getDisplayType(display),
            resolution = Display.getResolution(display),
            ratio = Display.getAspectRatio(display),
            diagonal = Display.getDiagonal(display, displayContext),
            size = Display.getDimensions(display, displayContext),
            ppi = Display.getPpi(display, displayContext),
            systemDensity = Display.getSystemDensityBucket(displayContext),
            refreshRate = Display.getRefreshRate(display),
            hdrTypes = Display.getHdrTypes(display),
            availableModes = Display.getAvailableModes(display),
            wideColorGamutStatus = Display.getWideColorGamutStatus(display)
        )
    }
}