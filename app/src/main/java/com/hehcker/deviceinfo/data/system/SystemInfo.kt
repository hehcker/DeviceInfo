package com.hehcker.deviceinfo.data.system

data class SystemInfo(
    val androidVersion: String,
    val codename: String,
    val firstApiLevel: String?,
    val androidPatch: String?,
    val vendorPatch: String?,
    val kernel: String,
    val build: String,
    val buildType: String,
    val buildDate: String?,
    val fingerprint: String,
    val baseband: String?,
    val uptime: String,
    val ssuStatus: String,
    val jvm: String?
)