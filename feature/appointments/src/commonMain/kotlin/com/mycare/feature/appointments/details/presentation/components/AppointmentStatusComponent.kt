package com.mycare.feature.appointments.details.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import com.mycare.core.ui.components.Spacer
import com.mycare.core.ui.theme.Dimens
import com.mycare.core.ui.theme.canceled
import com.mycare.core.ui.theme.completed
import com.mycare.core.ui.theme.scheduled
import com.mycare.feature.appointments.common.model.AppointmentStatusUiModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun AppointmentStatusComponent(status: AppointmentStatusUiModel) {
    Row(
        modifier = Modifier.padding(start = START_PADDING.dp),
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
private const val START_PADDING = 6
private const val ICON_PADDING = 14
