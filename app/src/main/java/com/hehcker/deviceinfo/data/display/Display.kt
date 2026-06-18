package com.hehcker.deviceinfo.data.display

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.view.Display
import org.lsposed.hiddenapibypass.HiddenApiBypass
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

object Display {
    private const val INCHES_TO_CM = 2.54

    // Display types from sdk
    private const val TYPE_INTERNAL = 1
    private const val TYPE_EXTERNAL = 2
    private const val TYPE_WIFI = 3
    private const val TYPE_OVERLAY = 4
    private const val TYPE_VIRTUAL = 5


    fun getDisplayName(display: Display): String {
        return display.name
    }

    fun getDisplayType(display: Display): String {
        return when (HiddenApiBypass.invoke(Display::class.java, display, "getType") as? Int) {
            TYPE_INTERNAL -> "Internal"
            TYPE_EXTERNAL -> "External"
            TYPE_WIFI -> "Wireless"
            TYPE_OVERLAY -> "Overlay"
            TYPE_VIRTUAL -> "Virtual"
            else -> "Unknown"
        }
    }

    fun getResolution(display: Display): String {
        val mode = display.mode
        val height = minOf(mode.physicalWidth, mode.physicalHeight)
        val width = maxOf(mode.physicalWidth, mode.physicalHeight)

        return "$width x $height"
    }

    private tailrec fun gcd(a: Int, b: Int): Int =
        if (b == 0) a else gcd(b, a % b)

    fun getAspectRatio(display: Display): String {
        val mode = display.mode
        val height = minOf(mode.physicalWidth, mode.physicalHeight)
        val width = maxOf(mode.physicalWidth, mode.physicalHeight)

        val d = gcd(width, height)

        val rw = width / d
        val rh = height / d

        return if (rw <= 30 && rh <= 30) {
            "$rw:$rh"
        } else {
            "%.1f:9".format(width.toFloat() / height * 9)
        }
    }

    fun getDiagonal(display: Display, context: Context): String {
        val metrics = context
            .createDisplayContext(display)
            .resources
            .displayMetrics

        val widthInches = display.mode.physicalWidth.toDouble() / metrics.xdpi
        val heightInches = display.mode.physicalHeight.toDouble() / metrics.ydpi

        val diagonalInches = sqrt(widthInches.pow(2.0) + heightInches.pow(2.0))

        val sizeInCm = diagonalInches * INCHES_TO_CM

        return "%.2f\" (%.2f cm)".format(diagonalInches, sizeInCm)
    }

    fun getDimensions(display: Display, context: Context): String {
        val metrics = context
            .createDisplayContext(display)
            .resources
            .displayMetrics

        val widthInches = display.mode.physicalWidth.toDouble() / metrics.xdpi
        val heightInches = display.mode.physicalHeight.toDouble() / metrics.ydpi

        val df = DecimalFormat("#.##")
        val widthCm = widthInches * INCHES_TO_CM
        val heightCm = heightInches * INCHES_TO_CM

        return "${df.format(widthCm)} x ${df.format(heightCm)} cm"
    }

    fun getPpi(display: Display, context: Context): String {
        val metrics = context
            .createDisplayContext(display)
            .resources
            .displayMetrics

        val widthPx = display.mode.physicalWidth.toDouble()
        val heightPx = display.mode.physicalHeight.toDouble()
        val widthInches = widthPx / metrics.xdpi
        val heightInches = heightPx / metrics.ydpi

        val diagonalPx = sqrt(widthPx.pow(2) + heightPx.pow(2))
        val diagonalInches = sqrt(widthInches.pow(2) + heightInches.pow(2))

        val ppi = diagonalPx / diagonalInches

        return "${ppi.roundToInt()}"
    }

    fun getSystemDensityBucket(context: Context): String {
        val density = context.resources.displayMetrics.density
        val dpi = (density * 160.0f).roundToInt()

        val bucket = when {
            dpi <= 120 -> "ldpi"
            dpi <= 160 -> "mdpi"
            dpi <= 213 -> "tvdpi"
            dpi <= 240 -> "hdpi"
            dpi <= 320 -> "xhdpi"
            dpi <= 480 -> "xxhdpi"
            else -> "xxxhdpi"
        }
        return "$dpi dpi ($bucket)"
    }

    fun getRefreshRate(display: Display): String {
        return "${round(display.refreshRate.toDouble(), 1)} Hz"
    }

    fun getHdrTypes(display: Display): List<String> {
        val hdrInts = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            display.supportedModes
                .flatMap { it.supportedHdrTypes.asIterable() }
                .distinct()
        } else {
            @Suppress("DEPRECATION")
            display.hdrCapabilities?.supportedHdrTypes?.distinct() ?: emptyList()
        }

        if (hdrInts.isEmpty()) return listOf("Unsupported")

        return hdrInts.sorted().map {
            when (it) {
                Display.HdrCapabilities.HDR_TYPE_DOLBY_VISION -> "Dolby Vision"
                Display.HdrCapabilities.HDR_TYPE_HDR10 -> "HDR10"
                Display.HdrCapabilities.HDR_TYPE_HDR10_PLUS -> "HDR10+"
                Display.HdrCapabilities.HDR_TYPE_HLG -> "Hybrid Log Gamma"
                else -> "Unknown ($it)"
            }
        }
    }

    fun getAvailableModes(display: Display): List<String> {
        return display.supportedModes.map { mode ->
            val w = maxOf(mode.physicalWidth, mode.physicalHeight)
            val h = minOf(mode.physicalWidth, mode.physicalHeight)
            "${h}x${w} @ ${round(mode.refreshRate.toDouble(), 1)}Hz"
        }.distinct()
    }

    fun getWideColorGamutStatus(display: Display): String {
        return if (display.isWideColorGamut) "Supported" else "Unsupported"
    }

    private fun round(value: Double, places: Int): Double {
        return value.toBigDecimal().setScale(places, RoundingMode.HALF_UP).toDouble()
    }
}