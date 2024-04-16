package com.mycare.feature.appointments.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mycare.core.ui.components.MCText
import com.mycare.core.ui.components.Spacer
import com.mycare.core.ui.theme.Dimens.Elevation.Large
import com.mycare.core.ui.theme.Dimens.Space
import com.mycare.core.ui.theme.Dimens.SpaceMedium
import com.mycare.feature.appointments.presentation.model.AppointmentUiModel

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
        Card(
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            elevation = CardDefaults.cardElevation(defaultElevation = Large),
        ) {
            Column(
                modifier = Modifier
                    .widthIn(min = DATE_MIN_WIDTH.dp)
                    .padding(all = Space),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MCText.HeadlineSmall(
                    text = appointment.day,
                    textColor = MaterialTheme.colorScheme.onPrimary,
                )
                MCText.BodyMedium(
                    text = appointment.month,
                    textColor = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
        Spacer(width = SpaceMedium)
        Column(
            modifier = Modifier
                .padding(all = SpaceMedium)
                .weight(1f)
        ) {
            MCText.TitleMedium(text = appointment.name)
            Spacer(height = SpaceMedium)
            MCText.BodyMedium(text = appointment.time)
        }
        //TODO Not being resolved on iOS
        /*Icon(
            modifier = Modifier.size(ICON_SIZE.dp),
            tint = MaterialTheme.colorScheme.primary,
            painter = painterResource(Res.drawable.ic_chevron_right),
            contentDescription = null,
        )*/
    }
}

private const val ICON_SIZE = 36
private const val DATE_MIN_WIDTH = 90