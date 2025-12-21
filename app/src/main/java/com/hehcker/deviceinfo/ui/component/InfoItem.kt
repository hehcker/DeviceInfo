package com.hehcker.deviceinfo.ui.component

data class InfoItem(
    val label: String,
    val value: String
)

fun MutableList<InfoItem>.addIfValid(label: String, value: String?) {
    if (!value.isNullOrBlank() && !value.equals("unknown", ignoreCase = true)) {
        add(InfoItem(label, value))
    }
}