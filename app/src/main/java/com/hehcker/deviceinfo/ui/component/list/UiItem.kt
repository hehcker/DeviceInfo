package com.hehcker.deviceinfo.ui.component.list

data class UiItem(
    val label: String,
    val value: String,
    val isClickable: Boolean = false
)

fun MutableList<UiItem>.addIfValid(
    label: String,
    value: String?,
    isClickable: Boolean = false
) {
    if (!value.isNullOrBlank() && !value.equals("unknown", ignoreCase = true)) {
        add(UiItem(label, value, isClickable))
    }
}