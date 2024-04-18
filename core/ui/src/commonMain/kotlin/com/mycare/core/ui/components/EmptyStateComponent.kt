@file:OptIn(ExperimentalResourceApi::class)

package com.mycare.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.mycare.core.ui.components.MCText.BodyLarge
import com.mycare.core.ui.theme.Dimens.Space
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import androidx.compose.foundation.layout.Spacer as ComposeSpacer

@Composable
fun EmptyStateComponent(
    modifier: Modifier = Modifier,
    imageRes: DrawableResource,
    textRes: StringResource,
) {
    BaseEmptyStateComponent(
        modifier = modifier,
        imageRes = imageRes,
        text = stringResource(resource = textRes),
    )
}

@Composable
fun EmptyStateComponent(
    modifier: Modifier = Modifier,
    imageRes: DrawableResource,
    text: String,
) {
    BaseEmptyStateComponent(
        modifier = modifier,
        imageRes = imageRes,
        text = text,
    )
}

@Composable
private fun BaseEmptyStateComponent(
    modifier: Modifier = Modifier,
    imageRes: DrawableResource,
    text: String,
) {
    Column(
        modifier = modifier
            .padding(start = Space, end = Space, bottom = Space)
            .fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
    ) {
        ComposeSpacer(modifier = Modifier.fillMaxHeight(SPACER_HEIGHT_FACTOR_TOP))
        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(imageRes),
            contentDescription = null,
        )
        BodyLarge(
            text = text,
            textAlign = TextAlign.Center,
        )
        ComposeSpacer(modifier = Modifier.fillMaxHeight(SPACER_HEIGHT_FACTOR))
    }
}

private const val SPACER_HEIGHT_FACTOR = 0.2f
private const val SPACER_HEIGHT_FACTOR_TOP = 0.1f
