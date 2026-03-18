package com.hehcker.deviceinfo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hehcker.deviceinfo.R

@OptIn(ExperimentalTextApi::class)
val GSansFlexFamily = FontFamily(
    Font(
        R.font.googlesansflex,
        weight = FontWeight.Light,
        variationSettings = FontVariation.Settings(
            FontVariation.Setting("ROND", 100f),
            FontVariation.Setting("XTRA", 520f),
            FontVariation.Setting("YOPQ", 90f),
            FontVariation.Setting("YTLC", 505f),
            FontVariation.weight(300)
        )
    ),
    Font(
        R.font.googlesansflex,
        weight = FontWeight.Normal,
        variationSettings = FontVariation.Settings(
            FontVariation.Setting("ROND", 100f),
            FontVariation.Setting("XTRA", 520f),
            FontVariation.Setting("YOPQ", 90f),
            FontVariation.Setting("YTLC", 505f),
            FontVariation.weight(400)
        )
    ),
    Font(
        R.font.googlesansflex,
        weight = FontWeight.Medium,
        variationSettings = FontVariation.Settings(
            FontVariation.Setting("ROND", 100f),
            FontVariation.Setting("XTRA", 520f),
            FontVariation.Setting("YOPQ", 90f),
            FontVariation.Setting("YTLC", 505f),
            FontVariation.weight(500)
        )
    ),
    Font(
        R.font.googlesansflex,
        weight = FontWeight.SemiBold,
        variationSettings = FontVariation.Settings(
            FontVariation.Setting("ROND", 100f),
            FontVariation.Setting("XTRA", 520f),
            FontVariation.Setting("YOPQ", 90f),
            FontVariation.Setting("YTLC", 505f),
            FontVariation.weight(600)
        )
    ),
    Font(
        R.font.googlesansflex,
        weight = FontWeight.Bold,
        variationSettings = FontVariation.Settings(
            FontVariation.Setting("ROND", 100f),
            FontVariation.Setting("XTRA", 520f),
            FontVariation.Setting("YOPQ", 90f),
            FontVariation.Setting("YTLC", 505f),
            FontVariation.weight(700)
        )
    )
)

// Set of Material typography styles to start with
val Typography = Typography().run {
    copy(
        bodyLarge = bodyLarge.copy(fontFamily = GSansFlexFamily),
        bodyMedium = bodyMedium.copy(fontFamily = GSansFlexFamily),
        bodySmall = bodySmall.copy(fontFamily = GSansFlexFamily),

        titleLarge = titleLarge.copy(fontFamily = GSansFlexFamily),
        titleMedium = titleMedium.copy(fontFamily = GSansFlexFamily),
        titleSmall = titleSmall.copy(fontFamily = GSansFlexFamily),

        labelLarge = labelLarge.copy(fontFamily = GSansFlexFamily),
        labelMedium = labelMedium.copy(fontFamily = GSansFlexFamily),
        labelSmall = labelSmall.copy(fontFamily = GSansFlexFamily),

        headlineLarge = headlineLarge.copy(fontFamily = GSansFlexFamily),
        headlineMedium = headlineMedium.copy(fontFamily = GSansFlexFamily),
        headlineSmall = headlineSmall.copy(fontFamily = GSansFlexFamily),

        displayLarge = displayLarge.copy(fontFamily = GSansFlexFamily),
        displayMedium = displayMedium.copy(fontFamily = GSansFlexFamily),
        displaySmall = displaySmall.copy(fontFamily = GSansFlexFamily),
    )
}