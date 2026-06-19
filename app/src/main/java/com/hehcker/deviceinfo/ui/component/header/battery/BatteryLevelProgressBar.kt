package com.hehcker.deviceinfo.ui.component.header.battery

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.dp

//ref: hdrd13/SavingsFund

@Composable
fun BatteryLevelProgressBar(
    progress: Float,
    modifier: Modifier = Modifier
) {
    val safeProgress = progress.coerceIn(0f, 1f)

    val trackColor = MaterialTheme.colorScheme.secondaryContainer
    val progressColor = MaterialTheme.colorScheme.primary

    val trackHeight = 40.dp
    val outerRadiusDp = 16.dp
    val innerRadiusDp = 2.dp
    val gapDp = 2.dp
    val stopPaddingDp = 4.dp
    val stopRadiusDp = 2.dp

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(trackHeight)
    ) {
        val w = size.width
        val h = size.height

        val outerRadiusPx = outerRadiusDp.toPx()
        val innerRadiusPx = innerRadiusDp.toPx()
        val gapPx = gapDp.toPx()

        val progressX = w * safeProgress

        val capsulePath = Path().apply {
            addRoundRect(RoundRect(0f, 0f, w, h, CornerRadius(outerRadiusPx, outerRadiusPx)))
        }

        clipPath(capsulePath) {

            val trackStart = if (safeProgress <= 0f) 0f else progressX + gapPx

            if (trackStart < w) {
                val trackInnerRadius = innerRadiusPx * (trackStart / outerRadiusPx).coerceIn(0f, 1f)

                val trackPath = Path().apply {
                    addRoundRect(
                        RoundRect(
                            left = trackStart, top = 0f, right = w, bottom = h,
                            topLeftCornerRadius = CornerRadius(trackInnerRadius, trackInnerRadius),
                            bottomLeftCornerRadius = CornerRadius(
                                trackInnerRadius,
                                trackInnerRadius
                            ),
                            topRightCornerRadius = CornerRadius.Zero,
                            bottomRightCornerRadius = CornerRadius.Zero
                        )
                    )
                }
                drawPath(trackPath, trackColor)

                val stopSize = stopRadiusDp.toPx() * 2f
                val stopCenterX = w - stopPaddingDp.toPx() - stopSize / 2f

                if (stopCenterX - stopSize / 2f > trackStart) {
                    drawRoundRect(
                        color = progressColor,
                        topLeft = Offset(
                            stopCenterX - stopSize / 2f,
                            h / 2f - stopSize / 2f
                        ),
                        size = Size(stopSize, stopSize),
                        cornerRadius = CornerRadius(
                            stopRadiusDp.toPx(),
                            stopRadiusDp.toPx()
                        )
                    )
                }
            }

            if (safeProgress > 0f) {
                val distanceToRight = w - progressX
                val indicatorInnerRadius =
                    innerRadiusPx * (distanceToRight / outerRadiusPx).coerceIn(0f, 1f)

                val indicatorPath = Path().apply {
                    addRoundRect(
                        RoundRect(
                            left = 0f, top = 0f, right = progressX, bottom = h,
                            topLeftCornerRadius = CornerRadius.Zero,
                            bottomLeftCornerRadius = CornerRadius.Zero,
                            topRightCornerRadius = CornerRadius(
                                indicatorInnerRadius,
                                indicatorInnerRadius
                            ),
                            bottomRightCornerRadius = CornerRadius(
                                indicatorInnerRadius,
                                indicatorInnerRadius
                            )
                        )
                    )
                }
                drawPath(indicatorPath, progressColor)
            }
        }
    }
}