package com.mycare.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class TextProperties(
    val fontSize: TextUnit,
    val fontWeight: FontWeight,
    val lineHeight: TextUnit,
)

@Composable
fun getTextProperties(mcText: MCText): TextProperties {
    return when (mcText) {
        MCText.HeadlineLarge -> TextProperties(
            fontSize = 32.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 40.sp,
        )
        MCText.HeadlineMedium -> TextProperties(
            fontSize = 28.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 36.sp,
        )
        MCText.HeadlineSmall -> TextProperties(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 32.sp,
        )
        MCText.TitleLarge -> TextProperties(
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 28.sp,
        )
        MCText.TitleMedium -> TextProperties(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 24.sp,
        )
        MCText.BodyLarge -> TextProperties(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 24.sp,
        )
        MCText.BodyMedium -> TextProperties(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 20.sp,
        )
        MCText.BodySmall -> TextProperties(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 16.sp,
        )

        MCText.LabelLarge -> TextProperties(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp,
        )
    }
}