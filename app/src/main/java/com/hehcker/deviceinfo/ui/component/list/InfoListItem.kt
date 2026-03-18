package com.hehcker.deviceinfo.ui.component.list

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.hehcker.deviceinfo.ui.theme.CustomColors.listItemColors

@Composable
fun InfoListItem(
    headlineContent: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    overlineContent: @Composable (() -> Unit)? = null,
    supportingContent: @Composable (() -> Unit)? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    colors: ListItemColors = listItemColors,
    tonalElevation: Dp = ListItemDefaults.Elevation,
    shadowElevation: Dp = ListItemDefaults.Elevation,
    items: Int,
    index: Int
) {
    val top = (
            if (items == 1 || index == 0) 20.dp
            else 4.dp
            )
    val bottom = (
            if (items == 1 || index == items - 1) 20.dp
            else 4.dp
            )

    ListItem(
        headlineContent = headlineContent,
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = top.coerceAtLeast(0.dp),
                    topEnd = top.coerceAtLeast(0.dp),
                    bottomStart = bottom.coerceAtLeast(0.dp),
                    bottomEnd = bottom.coerceAtLeast(0.dp)
                )
            ),
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        colors = colors,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation
    )
}
