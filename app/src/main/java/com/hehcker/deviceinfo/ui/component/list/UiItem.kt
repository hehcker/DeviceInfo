package com.hehcker.deviceinfo.ui.component.list

data class UiItem(
    val label: String,
    val value: String
)

fun MutableList<UiItem>.addIfValid(label: String, value: String?) {
    if (!value.isNullOrBlank() && !value.equals("unknown", ignoreCase = true)) {
        add(UiItem(label, value))
    }
}