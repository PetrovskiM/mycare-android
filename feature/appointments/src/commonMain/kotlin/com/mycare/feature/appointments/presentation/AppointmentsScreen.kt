package com.mycare.feature.appointments.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.bumble.appyx.navigation.collections.ImmutableList
import com.mycare.core.ui.components.LoadingIndicator
import com.mycare.core.ui.components.MCText
import com.mycare.core.ui.components.MCToolbar
import com.mycare.core.ui.components.Spacer
import com.mycare.core.ui.extension.fadingEdge
import com.mycare.core.ui.theme.Dimens.Radius.XLarge
import com.mycare.core.ui.theme.Dimens.Space
import com.mycare.core.ui.theme.Dimens.SpaceMedium
import com.mycare.core.ui.theme.Dimens.SpaceXLarge
import com.mycare.core.ui.theme.Dimens.SpaceXXXLarge
import com.mycare.feature.appointments.presentation.components.AppointmentComponent
import com.mycare.feature.appointments.presentation.components.FutureAppointmentComponent
import com.mycare.feature.appointments.presentation.contract.AppointmentsState
import com.mycare.feature.appointments.presentation.contract.AppointmentsViewAction
import com.mycare.feature.appointments.presentation.contract.AppointmentsViewAction.Retry
import com.mycare.feature.appointments.presentation.model.AppointmentUiModel
import mycare.feature.appointments.generated.resources.Res
import mycare.feature.appointments.generated.resources.ic_person
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun AppointmentsScreen(
    state: AppointmentsState,
    onViewAction: (AppointmentsViewAction) -> Unit,
    navigateToAppointmentDetails: (String) -> Unit,
) {
    AppointmentsContent(
        state = state,
        navigateToAppointmentDetails = navigateToAppointmentDetails,
        onViewAction = onViewAction,
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun AppointmentsContent(
    state: AppointmentsState,
    navigateToAppointmentDetails: (String) -> Unit,
    onViewAction: (AppointmentsViewAction) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        MCToolbar.LargeToolbar(
            text = {
                Column(
                    modifier = Modifier
                        .padding(horizontal = Space)
                        .weight(1f),
                ) {
                    MCText.BodyLarge(text = "Hello")
                    MCText.TitleLarge(text = state.user)
                }
            },
            leadingButton = {
                Box(
                    modifier = Modifier
                        .padding(end = Space)
                        .clip(RoundedCornerShape(XLarge))
                        .size(SpaceXXXLarge)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(USER_ICON_SIZE_FACTOR),
                        painter = painterResource(Res.drawable.ic_person),
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = null,
                    )
                }
            },
        )
        Spacer(height = SpaceXLarge)
        AnimatedContent(
            targetState = state,
            contentKey = {
                Triple(
                    state.isLoading,
                    state.upcomingAppointment,
                    state.error,
                )
            },
        ) { state ->
            when {
                state.isLoading -> LoadingIndicator()
                state.upcomingAppointment != null -> AppointmentsComponent(
                    upcomingAppointment = state.upcomingAppointment,
                    futureAppointments = state.futureAppointments,
                    navigateToAppointmentDetails = navigateToAppointmentDetails,
                )

                state.error != null -> Column {
                    Text("Error State Placeholder")
                    Button(onClick = { onViewAction(Retry) }) {
                        Text("Retry")
                    }
                }

                else -> {
                    Text("Empty State Placeholder")
                }
            }
        }
    }
}

@Composable
private fun AppointmentsComponent(
    upcomingAppointment: AppointmentUiModel,
    futureAppointments: ImmutableList<AppointmentUiModel>,
    navigateToAppointmentDetails: (String) -> Unit,
) {
    Column {
        UpcomingAppointmentComponent(
            appointment = upcomingAppointment,
            navigateToAppointmentDetails = navigateToAppointmentDetails,
        )
        Spacer(height = SpaceXLarge)
        if (futureAppointments.isNotEmpty()) {
            FutureAppointmentsComponent(
                appointments = futureAppointments,
                navigateToAppointmentDetails = navigateToAppointmentDetails,
            )
        }
    }
}

@Composable
private fun UpcomingAppointmentComponent(
    appointment: AppointmentUiModel,
    navigateToAppointmentDetails: (String) -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f)),
        modifier = Modifier
            .padding(
                start = Space,
                end = Space,
                bottom = Space,
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Space),
        ) {
            MCText.HeadlineSmall(text = "Upcoming Appointment") // Todo not being resolved with Res
            Spacer(height = Space)
            AppointmentComponent(
                appointment = appointment,
                navigateToAppointmentDetails = navigateToAppointmentDetails,
            )
        }
    }
}

@Composable
private fun FutureAppointmentsComponent(
    appointments: ImmutableList<AppointmentUiModel>,
    navigateToAppointmentDetails: (String) -> Unit,
) {
    val scrollState = rememberLazyListState()
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f)),
        modifier = Modifier
            .padding(
                start = Space,
                end = Space,
                bottom = Space,
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Space),
        ) {
            MCText.HeadlineSmall(text = "Future Appointments")
            Spacer(height = Space)
            LazyColumn(
                modifier = Modifier
                    .fadingEdge(
                        canScrollForward = scrollState.canScrollForward,
                        canScrollBackward = scrollState.canScrollBackward,
                    ),
                state = scrollState,
            ) {
                items(appointments) {
                    Spacer(height = SpaceMedium)
                    FutureAppointmentComponent(
                        appointment = it,
                        navigateToAppointmentDetails = navigateToAppointmentDetails,
                    )
                    Spacer(height = SpaceMedium)
                }
            }
        }
    }
}

private const val USER_ICON_SIZE_FACTOR = 0.6f
