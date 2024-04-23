@file:OptIn(ExperimentalResourceApi::class)

package com.mycare.feature.appointments.details.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mycare.core.ui.components.AppointmentStatusComponent
import com.mycare.core.ui.components.ErrorComponent
import com.mycare.core.ui.components.LoadingIndicator
import com.mycare.core.ui.components.MCText.TitleMedium
import com.mycare.core.ui.components.Spacer
import com.mycare.core.ui.presentation.model.AppointmentStatusUiModel
import com.mycare.core.ui.presentation.model.AppointmentUiModel
import com.mycare.core.ui.presentation.model.LocationUiModel
import com.mycare.core.ui.theme.Dimens.Space
import com.mycare.core.ui.theme.Dimens.SpaceMedium
import com.mycare.core.ui.theme.Dimens.SpaceSmall
import com.mycare.core.ui.theme.Dimens.SpaceXLarge
import com.mycare.feature.appointments.details.presentation.components.BaseDetailsCard
import com.mycare.feature.appointments.details.presentation.components.DetailComponent
import com.mycare.feature.appointments.details.presentation.contract.AppointmentDetailsState
import com.mycare.feature.appointments.details.presentation.contract.AppointmentDetailsViewAction
import com.mycare.feature.appointments.details.presentation.contract.AppointmentDetailsViewAction.Retry
import mycare.feature.appointments.generated.resources.Res
import mycare.feature.appointments.generated.resources.appointment_details_estimated_duration
import mycare.feature.appointments.generated.resources.appointment_details_handled_by
import mycare.feature.appointments.generated.resources.appointment_details_on
import mycare.feature.appointments.generated.resources.appointment_details_on_title
import mycare.feature.appointments.generated.resources.appointments_details
import mycare.feature.appointments.generated.resources.appointments_location
import mycare.feature.appointments.generated.resources.common_back_cd
import mycare.feature.appointments.generated.resources.ic_back
import mycare.feature.appointments.generated.resources.ic_calendar
import mycare.feature.appointments.generated.resources.ic_call
import mycare.feature.appointments.generated.resources.ic_directions
import mycare.feature.appointments.generated.resources.ic_estimated_duration
import mycare.feature.appointments.generated.resources.ic_location
import mycare.feature.appointments.generated.resources.ic_person
import mycare.feature.appointments.generated.resources.ic_placeholder
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
    // TODO Update models, load coil images, add google map component and see if it requires lat lng or address
    // I think we keep the address field so that the staff can enter a manual address if google maps doesnt find it
    // but we have the exact lat lng for the pin
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
        ImageComponent(
            isLoading = state.isLoading,
            imageUrl = state.appointment?.location?.imageUrl,
            navigateBack = navigateBack,
        )
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
                    state.error != null -> ErrorComponent(
                        onClick = { onViewAction(Retry) },
                    )
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
            handledBy = appointment.handledBy,
            status = appointment.status,
            estimatedDuration = appointment.estimatedDuration,
        )
        Spacer(height = SpaceXLarge)
        LocationComponent(location = appointment.location)
    }
}

@Composable
private fun AppointmentComponent(
    name: String,
    day: String,
    month: String,
    time: String,
    handledBy: String,
    status: AppointmentStatusUiModel,
    estimatedDuration: Int?,
) {
    Spacer(height = SpaceSmall)
    BaseDetailsCard(title = Res.string.appointments_details) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Space),
        ) {
            TitleMedium(text = stringResource(Res.string.appointment_details_on_title, name))
            Spacer(height = SpaceMedium)
            DetailComponent(
                title = stringResource(Res.string.appointment_details_on, day, month),
                icon = Res.drawable.ic_calendar,
            )
            Spacer(height = SpaceSmall)
            DetailComponent(
                title = time,
                icon = Res.drawable.ic_time,
            )
            if (estimatedDuration != null) {
                Spacer(height = SpaceSmall)
                DetailComponent(
                    title = stringResource(
                        Res.string.appointment_details_estimated_duration,
                        estimatedDuration.toString(),
                    ),
                    icon = Res.drawable.ic_estimated_duration,
                )
            }
            Spacer(height = SpaceSmall)
            DetailComponent(
                title = stringResource(Res.string.appointment_details_handled_by, handledBy),
                icon = Res.drawable.ic_person,
            )
            Spacer(height = SpaceSmall)
            AppointmentStatusComponent(
                modifier = Modifier.padding(start = START_PADDING.dp),
                status = status,
            )
        }
    }
}

@Composable
private fun LocationComponent(location: LocationUiModel) {
    // TODO Add google maps compnent near end, maybe by that time there will be multiplatform support
    BaseDetailsCard(
        title = Res.string.appointments_location,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Space),
        ) {
            TitleMedium(text = location.name)
            Spacer(height = SpaceMedium)
            DetailComponent(
                title = location.address.name,
                icon = Res.drawable.ic_location,
            )
            location.address.additionalDirections?.let {
                Spacer(height = SpaceMedium)
                DetailComponent(
                    title = it,
                    icon = Res.drawable.ic_directions,
                )
            }
            location.phone?.let {
                Spacer(height = SpaceSmall)
                DetailComponent(
                    title = it,
                    icon = Res.drawable.ic_call,
                )
            }
        }
    }
}

@Composable
private fun BoxScope.ImageComponent(
    isLoading: Boolean,
    imageUrl: String?,
    navigateBack: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight(fraction = IMAGE_HEIGHT_FRACTION)
            .align(alignment = Alignment.TopCenter),
    ) {
        if (!isLoading) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                model = imageUrl,
                fallback = painterResource(Res.drawable.img_hospital),
                placeholder = painterResource(Res.drawable.ic_placeholder),
                contentDescription = null,
            )
        } else {
            Image(
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                painter = painterResource(Res.drawable.ic_placeholder),
                contentDescription = null,
            )
        }
        BackButton(onClick = navigateBack)
    }
}

@Composable
private fun BackButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(
                start = Space,
                end = Space,
                top = SpaceXLarge,
                bottom = Space,
            )
            .clip(shape = CircleShape)
            .clickable(onClick = onClick)
            .background(color = Color.Black.copy(alpha = BACK_ALPHA))
            .padding(all = SpaceMedium),
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_back),
            contentDescription = stringResource(Res.string.common_back_cd),
        )
    }
}

private const val BACK_ALPHA = 0.3f
private const val IMAGE_HEIGHT_FRACTION = 0.3f
private const val CONTENT_HEIGHT_FRACTION = 0.75f
private const val START_PADDING = 6
