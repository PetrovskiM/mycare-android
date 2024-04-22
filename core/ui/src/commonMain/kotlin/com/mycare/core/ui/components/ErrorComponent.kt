package com.mycare.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mycare.core.ui.components.MCText.BodyMedium
import com.mycare.core.ui.components.MCText.TitleMedium
import com.mycare.core.ui.theme.Dimens.Space
import mycare.core.ui.generated.resources.Res
import mycare.core.ui.generated.resources.common_error_message
import mycare.core.ui.generated.resources.common_retry
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ErrorComponent(
    message: StringResource = Res.string.common_error_message,
    ctaText: StringResource = Res.string.common_retry,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Space),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TitleMedium(text = stringResource(message))
        Button(onClick = onClick) {
            BodyMedium(text = stringResource(ctaText))
        }
    }
}
