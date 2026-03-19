package com.hehcker.deviceinfo.data.display

import android.content.Context
import android.hardware.display.DisplayManager
import android.view.WindowManager

object DisplayInfoProvider {
    fun get(context: Context): DisplayInfo {
        val displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        val display = displayManager.getDisplay(android.view.Display.DEFAULT_DISPLAY)
        context.createDisplayContext(display)

        val windowManager = context.getSystemService(WindowManager::class.java)
        val windowMetrics = windowManager.currentWindowMetrics

        val bounds = windowMetrics.bounds

        return DisplayInfo(
            resolution = Display.getResolution(bounds),
            ratio = Display.getAspectRatio(bounds),
            diagonal = Display.getDiagonal(bounds, context),
            size = Display.getDimensions(bounds, context),
            ppi = Display.getPpi(bounds, context),
            systemDensity = Display.getSystemDensityBucket(context),
            refreshRate = Display.getRefreshRate(display),
            hdrTypes = Display.getHdrTypes(display),
            availableModes = Display.getAvailableModes(display),
            wideColorGamutStatus = Display.getWideColorGamutStatus(display)
        )
    }
}