package com.hehcker.deviceinfo.ui.component.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.hehcker.deviceinfo.ui.theme.CustomColors.listItemColors

/**
 * overload that takes a just label and value
 * instead of composable units
 */
@Composable
fun ClickableListItem(
    label: String,
    value: String,
    onClick: () -> Unit,
    items: Int,
    index: Int,
    modifier: Modifier = Modifier,
    colors: ListItemColors = listItemColors,
    tonalElevation: Dp = ListItemDefaults.Elevation,
    shadowElevation: Dp = ListItemDefaults.Elevation,
) {
    ClickableListItem(
        headlineContent = { Text(text = label, style = typography.titleMedium) },
        supportingContent = { Text(text = value, style = typography.bodyMedium) },
        modifier = modifier,
        onClick = onClick,
        colors = colors,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
        items = items,
        index = index
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ClickableListItem(
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
    index: Int,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

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
            )
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
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