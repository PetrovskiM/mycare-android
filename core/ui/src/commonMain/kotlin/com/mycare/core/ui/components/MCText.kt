package com.mycare.core.ui.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed class MCText {
    data object HeadlineLarge : MCText()
    data object HeadlineMedium : MCText()
    data object HeadlineSmall : MCText()
    data object TitleLarge : MCText()
    data object TitleMedium : MCText()
    data object BodyLarge : MCText()
    data object BodyMedium : MCText()
    data object BodySmall : MCText()
    data object LabelLarge : MCText()

    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        text: String,
        textAlign: TextAlign? = null,
        textColor: Color? = null,
    ) {
        val properties = getTextProperties(mcText = this)
        Text(
            modifier = modifier,
            text = text,
            color = textColor ?: LocalTextStyle.current.color,
            fontSize = properties.fontSize,
            fontWeight = properties.fontWeight,
            lineHeight = properties.lineHeight,
            textAlign = textAlign
        )
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        text: StringResource,
        textAlign: TextAlign? = null,
        textColor: Color? = null,
    ) {
        val properties = getTextProperties(mcText = this)
        Text(
            modifier = modifier,
            text = stringResource(resource = text),
            color = textColor ?: LocalTextStyle.current.color,
            fontSize = properties.fontSize,
            fontWeight = properties.fontWeight,
            lineHeight = properties.lineHeight,
            textAlign = textAlign
        )
    }
}