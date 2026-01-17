package com.hehcker.deviceinfo.data.display

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.view.Display
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

object Display {
    private const val INCHES_TO_CM = 2.54

    fun getResolution(bounds: Rect): String {
        return "${bounds.height()} x ${bounds.width()}"
    }

    fun getAspectRatio(bounds: Rect): String {
        val height = bounds.height()
        val width = bounds.width()

        val gcd = (width / 720.0) * 80
        val a = height / gcd
        val b = width / gcd

        val formatA = DecimalFormat("#.#")
        val formatB = DecimalFormat("#.#")

        return if (a > b) {
            "${formatB.format(b)}:${formatA.format(a)}"
        } else {
            "${formatA.format(a)}:${formatB.format(b)}"
        }
    }

    fun getDiagonal(bounds: Rect, context: Context): String {
        val metrics = context.resources.displayMetrics

        val widthInches = bounds.width() / metrics.xdpi.toDouble()
        val heightInches = bounds.height() / metrics.ydpi.toDouble()

        val diagonalInches = sqrt(widthInches.pow(2.0) + heightInches.pow(2.0))

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        val sizeInCm = diagonalInches * INCHES_TO_CM

        return "${df.format(diagonalInches)}\" (${df.format(sizeInCm)} cm)"
    }

    fun getDimensions(bounds: Rect, context: Context): String {
        val metrics = context.resources.displayMetrics

        val widthInches = bounds.width() / metrics.xdpi.toDouble()
        val heightInches = bounds.height() / metrics.ydpi.toDouble()

        val df = DecimalFormat("#.##")
        val widthCm = widthInches * INCHES_TO_CM
        val heightCm = heightInches * INCHES_TO_CM

        return "${df.format(widthCm)} x ${df.format(heightCm)} cm"
    }

    fun getPpi(context: Context): String {
        return "${context.resources.displayMetrics.densityDpi} dpi"
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
            display.hdrCapabilities.supportedHdrTypes.distinct()
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