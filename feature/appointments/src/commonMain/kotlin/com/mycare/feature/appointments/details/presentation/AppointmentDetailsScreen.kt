@file:OptIn(ExperimentalResourceApi::class)

package com.mycare.feature.appointments.details.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.mycare.core.ui.components.LoadingIndicator
import com.mycare.core.ui.components.MCText.TitleMedium
import com.mycare.core.ui.components.Spacer
import com.mycare.core.ui.theme.Dimens.Space
import com.mycare.core.ui.theme.Dimens.SpaceMedium
import com.mycare.core.ui.theme.Dimens.SpaceSmall
import com.mycare.core.ui.theme.Dimens.SpaceXLarge
import com.mycare.feature.appointments.common.model.AppointmentStatusUiModel
import com.mycare.feature.appointments.details.presentation.components.AppointmentStatusComponent
import com.mycare.feature.appointments.details.presentation.components.BaseDetailsCard
import com.mycare.feature.appointments.details.presentation.components.DetailComponent
import com.mycare.feature.appointments.details.presentation.contract.AppointmentDetailsState
import com.mycare.feature.appointments.details.presentation.contract.AppointmentDetailsViewAction
import com.mycare.feature.appointments.details.presentation.contract.AppointmentDetailsViewAction.Retry
import com.mycare.feature.appointments.presentation.model.AppointmentUiModel
import mycare.feature.appointments.generated.resources.Res
import mycare.feature.appointments.generated.resources.appointments_details
import mycare.feature.appointments.generated.resources.appointments_location
import mycare.feature.appointments.generated.resources.common_back_cd
import mycare.feature.appointments.generated.resources.ic_back
import mycare.feature.appointments.generated.resources.ic_calendar
import mycare.feature.appointments.generated.resources.ic_call
import mycare.feature.appointments.generated.resources.ic_location
import mycare.feature.appointments.generated.resources.ic_person
import mycare.feature.appointments.generated.resources.ic_time
import mycare.feature.appointments.generated.resources.img_hospital
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun AppointmentDetailsScreen(
    state: AppointmentDetailsState,
    onViewAction: (AppointmentDetailsViewAction) -> Unit,
    navigateBack: () -> Unit,
) {
    AppointmentDetailsContent(
        state = state,
        onViewAction = onViewAction,
        navigateBack = navigateBack,
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun AppointmentDetailsContent(
    state: AppointmentDetailsState,
    onViewAction: (AppointmentDetailsViewAction) -> Unit,
    navigateBack: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxHeight(fraction = IMAGE_HEIGHT_FRACTION)
                .align(alignment = Alignment.TopCenter),
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                painter = painterResource(Res.drawable.img_hospital),
                contentDescription = null,
            )
            Box(
                modifier = Modifier
                    .padding(
                        start = Space,
                        end = Space,
                        top = SpaceXLarge,
                        bottom = Space,
                    )
                    .clip(shape = CircleShape)
                    .clickable(onClick = navigateBack)
                    .background(color = Color.Black.copy(alpha = BACK_ALPHA))
                    .padding(all = SpaceMedium),
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = stringResource(Res.string.common_back_cd),
                )
            }
        }
        AnimatedContent(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.BottomCenter),
            targetState = state,
        ) { state ->
            Column(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = Space,
                            topEnd = Space,
                        ),
                    )
                    .fillMaxHeight(fraction = CONTENT_HEIGHT_FRACTION)
                    .background(color = MaterialTheme.colorScheme.background)
                    .verticalScroll(state = rememberScrollState()),
            ) {
                when {
                    state.isLoading -> LoadingIndicator()
                    state.appointment != null -> AppointmentInfoComponent(appointment = state.appointment)
                    state.error != null -> Column {
                        Text("Error placeholder")
                        Button(onClick = { onViewAction(Retry) }) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AppointmentInfoComponent(appointment: AppointmentUiModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = Space),
    ) {
        AppointmentComponent(
            name = appointment.name,
            day = appointment.day,
            month = appointment.month,
            time = appointment.time,
        )
        Spacer(height = SpaceXLarge)
        LocationComponent()
    }
}

@Composable
private fun AppointmentComponent(
    name: String,
    day: String,
    month: String,
    time: String,
) {
    BaseDetailsCard(title = Res.string.appointments_details) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Space),
        ) {
            TitleMedium(text = "Your appointment for $name is on:")
            Spacer(height = SpaceMedium)
            DetailComponent(
                title = "$day, $month",
                icon = Res.drawable.ic_calendar,
            )
            Spacer(height = SpaceSmall)
            DetailComponent(
                title = time,
                icon = Res.drawable.ic_time,
            )
            Spacer(height = SpaceSmall)
            DetailComponent(
                title = "Handled by Marci",
                icon = Res.drawable.ic_person,
            )
            Spacer(height = SpaceSmall)
            AppointmentStatusComponent(status = AppointmentStatusUiModel.COMPLETED)
        }
    }
}

@Composable
private fun LocationComponent() {
    BaseDetailsCard(
        title = Res.string.appointments_location,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Space),
        ) {
            TitleMedium(text = "Location Name")
            Spacer(height = SpaceMedium)
            DetailComponent(
                title = "Street Address",
                icon = Res.drawable.ic_location,
            )

            Spacer(height = SpaceSmall)
            DetailComponent(
                title = "555-333",
                icon = Res.drawable.ic_call,
            )
        }
    }
}

private const val BACK_ALPHA = 0.3f
private const val IMAGE_HEIGHT_FRACTION = 0.3f
private const val CONTENT_HEIGHT_FRACTION = 0.75f
