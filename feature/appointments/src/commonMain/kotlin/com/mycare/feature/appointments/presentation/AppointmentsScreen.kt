package com.mycare.feature.appointments.presentation

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.bumble.appyx.navigation.collections.ImmutableList
import com.bumble.appyx.navigation.collections.immutableListOf
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
import com.mycare.feature.appointments.presentation.model.AppointmentUiModel
import com.mycare.feature.appointments.presentation.model.fakeAppointment
import mycare.feature.appointments.generated.resources.Res
import mycare.feature.appointments.generated.resources.ic_person
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun AppointmentsScreen(
    state: AppointmentsState,
    navigateToAppointmentDetails: (String) -> Unit,
) {

    AppointmentsContent(
        state = state,
        navigateToAppointmentDetails = navigateToAppointmentDetails,
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun AppointmentsContent(
    state: AppointmentsState,
    navigateToAppointmentDetails: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        MCToolbar.LargeToolbar(
            text = {
                Column(modifier = Modifier.padding(horizontal = Space)) {
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
                        modifier = Modifier.fillMaxSize(0.6f),
                        painter = painterResource(Res.drawable.ic_person),
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = null,
                    )
                }
            }
        )
        Spacer(height = SpaceXLarge)
        //TODO possibly have a sealed interface of AppointsmentsState that has State and EmptyState
        if (state.upcomingAppointment != null) {
            UpcomingAppointmentComponent(
                appointment = state.upcomingAppointment,
                navigateToAppointmentDetails = navigateToAppointmentDetails,
            )
        }
        Spacer(height = SpaceXLarge)
        if (state.futureAppointments.isNotEmpty()) {
            FutureAppointmentsComponent(
                appointments = state.futureAppointments,
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
                bottom = Space
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Space)
        ) {
            MCText.HeadlineSmall(text = "Upcoming Appointment") //Todo not being resolved with Res
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
                bottom = Space
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Space)
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

/*@Composable
private fun FutureAppointmentsComponentFallback(appointments: ImmutableList<AppointmentUiModel>) {
    Box(
        modifier = Modifier
            .padding(
                start = Space,
                end = Space,
                bottom = Space
            )
            .shadow(
                elevation = Medium,
                shape = RoundedCornerShape(XLarge),
                spotColor = Color.Transparent,
                ambientColor = DefaultShadowColor.copy(alpha = BACKGROUND_ALPHA),
            )
            .background(color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = BACKGROUND_ALPHA))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Space)
        ) {
            MCText.HeadlineSmall(text = "Future Appointments")
            Spacer(height = Space)
            LazyColumn {
                items(appointments) {
                    Spacer(height = Space)
                    FutureAppointmentComponent(appointment = it)
                }
            }
        }
    }
}

private const val BACKGROUND_ALPHA = 0.15f
 */