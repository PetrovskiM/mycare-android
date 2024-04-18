@file:OptIn(ExperimentalResourceApi::class)

package com.mycare.feature.appointments.details.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import com.mycare.core.ui.components.MCText.BodyMedium
import com.mycare.core.ui.components.Spacer
import com.mycare.core.ui.theme.Dimens.SpaceMedium
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun DetailComponent(
    title: StringResource,
    icon: DrawableResource,
) {
    BaseDetailComponent(
        title = stringResource(title),
        icon = icon,
    )
}

@Composable
internal fun DetailComponent(
    title: String,
    icon: DrawableResource,
) {
    BaseDetailComponent(
        title = title,
        icon = icon,
    )
}

@Composable
private fun BaseDetailComponent(
    title: String,
    icon: DrawableResource,
) {
    Row(verticalAlignment = CenterVertically) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
        )
        Spacer(width = SpaceMedium)
        BodyMedium(text = title)
    }
}
