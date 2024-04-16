package com.mycare.feature.appointments.details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mycare.core.ui.components.MCToolbar
import com.mycare.core.ui.components.Spacer
import com.mycare.core.ui.theme.Dimens

@Composable
fun AppointmentDetailsScreen(
    viewModel: AppointmentDetailsViewModel,
) {
    AppointmentDetailsContent()
}

@Composable
private fun AppointmentDetailsContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        MCToolbar.LargeToolbar(
            text = "Appointment Details",
        )
        Spacer(height = Dimens.SpaceXLarge)

    }
}