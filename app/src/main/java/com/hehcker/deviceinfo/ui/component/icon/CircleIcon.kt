package com.hehcker.deviceinfo.ui.component.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hehcker.deviceinfo.R

// ref: https://github.com/crdroidandroid/android_vendor_addons/blob/16.0/sdk/commonutils/src/com/android/crdroid/utils/IconTinterUtils.kt
private val MATERIAL_COLOR_BG_RES_IDS = intArrayOf(
    R.color.m3_ref_palette_blue90,
    R.color.m3_ref_palette_pink90,
    R.color.m3_ref_palette_orange90,
    R.color.m3_ref_palette_yellow90,
    R.color.m3_ref_palette_blue_variant90,
    R.color.m3_ref_palette_green90,
    R.color.m3_ref_palette_grey90,
    R.color.m3_ref_palette_cyan90,
    R.color.m3_ref_palette_red90,
    R.color.m3_ref_palette_purple90
)
private val MATERIAL_COLOR_FG_RES_IDS = intArrayOf(
    R.color.m3_ref_palette_blue30,
    R.color.m3_ref_palette_pink30,
    R.color.m3_ref_palette_orange30,
    R.color.m3_ref_palette_yellow30,
    R.color.m3_ref_palette_blue_variant30,
    R.color.m3_ref_palette_green30,
    R.color.m3_ref_palette_grey30,
    R.color.m3_ref_palette_cyan30,
    R.color.m3_ref_palette_red30,
    R.color.m3_ref_palette_purple30
)

@Composable
fun CircleIcon(
    icon: ImageVector,
    contentDescription: String?,
    colorIndex: Int,
    modifier: Modifier = Modifier,
    circleSize: Dp = 40.dp,
    iconSize: Dp = 22.dp
) {
    val bgColor = colorResource(
        id = MATERIAL_COLOR_BG_RES_IDS[colorIndex % MATERIAL_COLOR_BG_RES_IDS.size]
    )
    val fgColor = colorResource(
        id = MATERIAL_COLOR_FG_RES_IDS[colorIndex % MATERIAL_COLOR_FG_RES_IDS.size]
    )

    Box(
        modifier = modifier
            .size(circleSize)
            .clip(CircleShape)
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = fgColor,
            modifier = Modifier.size(iconSize)
        )
    }
}