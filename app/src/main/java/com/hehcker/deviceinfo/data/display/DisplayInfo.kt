package com.hehcker.deviceinfo.data.display

data class DisplayInfo(
    val name: String,
    val type: String,
    val resolution: String,
    val ratio: String,
    val diagonal: String,
    val size: String,
    val ppi: String,
    val systemDensity: String,
    val refreshRate: String,
    val hdrTypes: List<String>,
    val availableModes: List<String>,
    val wideColorGamutStatus: String
)
