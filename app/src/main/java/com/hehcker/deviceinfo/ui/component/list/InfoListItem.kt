package com.hehcker.deviceinfo.ui.component.list

import android.content.ClipData
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.toClipEntry
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.hehcker.deviceinfo.ui.theme.CustomColors.listItemColors
import kotlinx.coroutines.launch

/**
 * overload that takes a just label and value
 * instead of composable units
 * has a dropdown menu on long click for copying "label: value" or just "value"
 * @param enableCopyMenu enables dropdown menu
 * @param animateCornersOnPress enables corner rounding on click
 */
@Composable
fun InfoListItem(
    label: String,
    value: String,
    items: Int,
    index: Int,
    modifier: Modifier = Modifier,
    colors: ListItemColors = listItemColors,
    tonalElevation: Dp = ListItemDefaults.Elevation,
    shadowElevation: Dp = ListItemDefaults.Elevation,
    onClick: (() -> Unit)? = null,
    enableCopyMenu: Boolean = true,
    animateCornersOnPress: Boolean = true
) {
    val density = LocalDensity.current
    val clipboard = LocalClipboard.current
    val scope = rememberCoroutineScope()
    var showMenu by remember { mutableStateOf(false) }
    var pressOffsetX by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier.pointerInput(Unit) {
            awaitEachGesture {
                val down = awaitFirstDown(
                    requireUnconsumed = false,
                    pass = PointerEventPass.Initial
                )
                pressOffsetX = down.position.x
            }
        }
    ) {

        InfoListItem(
            headlineContent = { Text(text = label, style = typography.titleMedium) },
            supportingContent = { Text(text = value, style = typography.bodyMedium) },
            modifier = modifier,
            colors = colors,
            tonalElevation = tonalElevation,
            shadowElevation = shadowElevation,
            items = items,
            index = index,
            onClick = onClick,
            isMenuOpen = showMenu,
            animateCornersOnPress = animateCornersOnPress,
            onLongClick = {
                if (enableCopyMenu) {
                    showMenu = true
                }
            }
        )

        if (enableCopyMenu) {
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = {
                    showMenu = false
                },
                offset = DpOffset(
                    x = with(density) { pressOffsetX.toDp() },
                    y = 0.dp
                )
            ) {
                DropdownMenuItem(
                    text = {
                        Text("Copy")
                    },
                    onClick = {
                        scope.launch {
                            clipboard.setClipEntry(
                                ClipData.newPlainText(label, "$label: $value").toClipEntry()
                            )
                        }
                        showMenu = false
                    }
                )

                DropdownMenuItem(
                    text = {
                        Text("Copy value")
                    },
                    onClick = {
                        scope.launch {
                            clipboard.setClipEntry(
                                ClipData.newPlainText(label, "$value").toClipEntry()
                            )
                        }
                        showMenu = false
                    }
                )
            }
        }
    }
}

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
    index: Int,
    isMenuOpen: Boolean = false,
    animateCornersOnPress: Boolean = false,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val shouldAnimate = isMenuOpen || (animateCornersOnPress && isPressed)

    val topBase = if (items == 1 || index == 0) 20.dp else 4.dp
    val bottomBase = if (items == 1 || index == items - 1) 20.dp else 4.dp

    val top by animateDpAsState(targetValue = if (shouldAnimate) 24.dp else topBase)
    val bottom by animateDpAsState(targetValue = if (shouldAnimate) 24.dp else bottomBase)

    val containerColor by animateColorAsState(
        targetValue = if (shouldAnimate) {
            lerp(colors.containerColor, androidx.compose.ui.graphics.Color.Black, 0.08f)
        } else {
            colors.containerColor
        }
    )
    val effectiveColors = colors.copy(containerColor = containerColor)

    val clickableModifier =
        if (onClick != null || onLongClick != null) {
            Modifier.combinedClickable(
                interactionSource = interactionSource,
                onClick = { onClick?.invoke() },
                onLongClick = { onLongClick?.invoke() }
            )
        } else {
            Modifier
        }

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
            .then(clickableModifier),
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        colors = effectiveColors,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation
    )
}
