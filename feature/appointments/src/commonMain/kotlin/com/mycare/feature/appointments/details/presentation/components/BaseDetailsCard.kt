package com.mycare.feature.appointments.details.presentation.components

import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import com.mycare.core.ui.components.MCText.TitleLarge
import com.mycare.core.ui.components.Spacer
import com.mycare.core.ui.theme.Dimens.Elevation
import com.mycare.core.ui.theme.Dimens.SpaceMedium
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun BaseDetailsCard(
    title: StringResource,
    colors: CardColors = CardDefaults.cardColors(),
    content: @Composable () -> Unit,
) {
    TitleLarge(text = stringResource(title))
    Spacer(height = SpaceMedium)
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.Large),
        colors = colors,
    ) {
        content()
    }
}
