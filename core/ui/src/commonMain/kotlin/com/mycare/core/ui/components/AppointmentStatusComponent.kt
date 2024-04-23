package com.mycare.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.mycare.core.ui.components.MCText.BodyMedium
import com.mycare.core.ui.presentation.model.AppointmentStatusUiModel
import com.mycare.core.ui.theme.Dimens
import com.mycare.core.ui.theme.canceled
import com.mycare.core.ui.theme.completed
import com.mycare.core.ui.theme.scheduled
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AppointmentStatusComponent(
    modifier: Modifier = Modifier,
    status: AppointmentStatusUiModel,
) {
    Row(
        modifier = modifier,
        verticalAlignment = CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(size = CIRCLE_SIZE.dp)
                .shadow(elevation = Dimens.Elevation.Medium, shape = CircleShape)
                .clip(shape = CircleShape)
                .background(
                    when (status) {
                        AppointmentStatusUiModel.SCHEDULED -> MaterialTheme.colorScheme.scheduled
                        AppointmentStatusUiModel.COMPLETED -> MaterialTheme.colorScheme.completed
                        AppointmentStatusUiModel.CANCELED -> MaterialTheme.colorScheme.canceled
                    },
                ),
        )
        Spacer(width = ICON_PADDING.dp)
        BodyMedium(text = stringResource(status.text))
    }
}

private const val CIRCLE_SIZE = 12
private const val ICON_PADDING = 14
