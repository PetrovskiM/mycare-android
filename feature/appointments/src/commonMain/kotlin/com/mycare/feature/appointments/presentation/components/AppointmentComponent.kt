package com.mycare.feature.appointments.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mycare.core.ui.components.DateCardComponent
import com.mycare.core.ui.components.MCText
import com.mycare.core.ui.components.Spacer
import com.mycare.core.ui.presentation.model.AppointmentUiModel
import com.mycare.core.ui.theme.Dimens.SpaceMedium

@Composable
internal fun AppointmentComponent(
    appointment: AppointmentUiModel,
    navigateToAppointmentDetails: (String) -> Unit,
) {
    BaseAppointmentComponent(
        appointment = appointment,
        backgroundColor = MaterialTheme.colorScheme.primary,
        navigateToAppointmentDetails = navigateToAppointmentDetails,
    )
}

@Composable
internal fun FutureAppointmentComponent(
    appointment: AppointmentUiModel,
    navigateToAppointmentDetails: (String) -> Unit,
) {
    BaseAppointmentComponent(
        appointment = appointment,
        backgroundColor = MaterialTheme.colorScheme.tertiary,
        navigateToAppointmentDetails = navigateToAppointmentDetails,
    )
}

@Composable
private fun BaseAppointmentComponent(
    appointment: AppointmentUiModel,
    backgroundColor: Color,
    navigateToAppointmentDetails: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToAppointmentDetails(appointment.id) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DateCardComponent(
            backgroundColor = backgroundColor,
            day = appointment.day,
            month = appointment.month,
        )
        Spacer(width = SpaceMedium)
        Column(
            modifier = Modifier
                .padding(all = SpaceMedium)
                .weight(1f),
        ) {
            MCText.TitleMedium(text = appointment.name)
            Spacer(height = SpaceMedium)
            MCText.BodyMedium(text = appointment.time)
        }
        // TODO Not being resolved on iOS
        /*Icon(
            modifier = Modifier.size(ICON_SIZE.dp),
            tint = MaterialTheme.colorScheme.primary,
            painter = painterResource(Res.drawable.ic_chevron_right),
            contentDescription = null,
        )*/
    }
}

private const val ICON_SIZE = 36
