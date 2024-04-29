package com.mycare.feature.history.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import com.mycare.core.ui.components.AppointmentStatusComponent
import com.mycare.core.ui.components.DateCardComponent
import com.mycare.core.ui.components.ErrorComponent
import com.mycare.core.ui.components.LoadingIndicator
import com.mycare.core.ui.components.MCText
import com.mycare.core.ui.components.MCToolbar.LargeToolbar
import com.mycare.core.ui.components.Spacer
import com.mycare.core.ui.presentation.model.AddressUiModel
import com.mycare.core.ui.presentation.model.AppointmentRatingUiModel
import com.mycare.core.ui.presentation.model.AppointmentStatusUiModel
import com.mycare.core.ui.presentation.model.AppointmentUiModel
import com.mycare.core.ui.presentation.model.LocationUiModel
import com.mycare.core.ui.theme.Dimens.Space
import com.mycare.core.ui.theme.Dimens.SpaceMedium
import com.mycare.core.ui.theme.Dimens.SpaceSmall
import com.mycare.core.ui.util.ImmutableList
import com.mycare.core.ui.util.emptyImmutableList
import com.mycare.core.ui.util.immutableListOf
import com.mycare.feature.history.presentation.components.FiltersBottomSheet
import com.mycare.feature.history.presentation.components.HistoryFiltersComponent
import com.mycare.feature.history.presentation.components.SearchComponent
import com.mycare.feature.history.presentation.contract.HistoryState
import com.mycare.feature.history.presentation.contract.HistoryViewAction
import com.mycare.feature.history.presentation.contract.HistoryViewAction.FiltersCanceled
import com.mycare.feature.history.presentation.contract.HistoryViewAction.FiltersSaved
import com.mycare.feature.history.presentation.contract.HistoryViewAction.OpenFilterBottomSheet
import com.mycare.feature.history.presentation.contract.HistoryViewAction.Retry
import com.mycare.feature.history.presentation.contract.HistoryViewAction.Search
import com.mycare.feature.history.presentation.contract.HistoryViewAction.SearchQueryChanged
import com.mycare.feature.history.presentation.model.HistoryFilterUiModel
import com.mycare.feature.history.presentation.model.HistoryUiModel
import mycare.feature.history.generated.resources.Res
import mycare.feature.history.generated.resources.history
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun HistoryScreen(
    state: HistoryState,
    onViewAction: (HistoryViewAction) -> Unit,
    navigateToAppointmentDetails: suspend (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    if (WindowInsets.ime.getBottom(LocalDensity.current) <= 0) {
        focusManager.clearFocus()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
    ) {
        HistoryToolbarComponent(
            searchQuery = state.searchQuery,
            hasSelectedFilters = state.hasSelectedFilters,
            isLoading = state.isLoading,
            onViewAction = onViewAction,
        )
        AnimatedContent(
            targetState = state,
            contentKey = {
                Pair(state.isLoading, state.appointments)
            },
        ) { state ->
            when {
                state.isLoading -> LoadingIndicator()
                state.appointments.isNotEmpty() -> HistoryAppointmentsComponent(appointments = state.appointments)
                state.error != null -> ErrorComponent(
                    onClick = { onViewAction(Retry) },
                )

                else -> {
                    Text("Empty State Placeholder")
                }
            }
        }

        if (state.isFiltersBottomSheetShown) {
            FiltersBottomSheet(
                onDismiss = { onViewAction(FiltersCanceled) },
                onFilterSaved = { onViewAction(FiltersSaved) },
                onFilterSelected = {},
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun HistoryToolbarComponent(
    searchQuery: String,
    hasSelectedFilters: Boolean,
    isLoading: Boolean,
    onViewAction: (HistoryViewAction) -> Unit,
) {
    LargeToolbar(
        text = Res.string.history,
        extraContent = {
            if (!isLoading) {
                SearchComponent(
                    query = searchQuery,
                    queryChanged = { onViewAction(SearchQueryChanged(it)) },
                    onSearch = { onViewAction(Search) },
                )
                Spacer(height = Space)
                HistoryFiltersComponent(
                    filters = filters,
                    onFilterClick = { onViewAction(OpenFilterBottomSheet("")) },
                    hasSelectedFilters = hasSelectedFilters,
                )
                Spacer(height = SpaceMedium)
            }
        },
    )
}

@Composable
private fun HistoryAppointmentsComponent(appointments: ImmutableList<HistoryUiModel>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = Space),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(appointments) {
            HistoryAppointmentComponent(appointment = it)
        }
    }
}

@Composable
private fun HistoryAppointmentComponent(appointment: HistoryUiModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = SpaceMedium),
    ) {
        Row(
            modifier = Modifier.padding(all = Space),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DateCardComponent(
                backgroundColor = MaterialTheme.colorScheme.primary,
                day = appointment.day,
                month = appointment.month,
            )
            Spacer(width = SpaceMedium)
            Column(
                modifier = Modifier
                    .padding(all = SpaceMedium)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                MCText.TitleLarge(text = appointment.name)
                Spacer(height = SpaceSmall)
                MCText.BodyMedium(text = appointment.time)
                MCText.BodyMedium(text = appointment.locationName)
                AppointmentStatusComponent(status = appointment.status)
            }
        }
    }
}

internal val filters = immutableListOf(
    HistoryFilterUiModel(
        name = "Rating",
        isActive = false,
    ),
    HistoryFilterUiModel(
        name = "Location",
        isActive = true,
    ),
    HistoryFilterUiModel(
        name = "Date",
        isActive = false,
    ),
    HistoryFilterUiModel(
        name = "Status",
        isActive = false,
    ),
)

val appointments = immutableListOf(
    AppointmentUiModel(
        id = "1",
        name = "First Appointment",
        day = "15",
        month = "Octoner",
        time = "15:45",
        handledBy = "Marci",
        estimatedDuration = 15,
        location = LocationUiModel(
            name = "Location Name",
            address = AddressUiModel(
                name = "15, Street Name",
                additionalDirections = "Right, first left",
            ),
            phone = "555-333",
            imageUrl = null,
        ),
        conclusion = null,
        status = AppointmentStatusUiModel.COMPLETED,
        rating = AppointmentRatingUiModel(emptyImmutableList()),
    ),
    AppointmentUiModel(
        id = "2",
        name = "Second Appointment",
        day = "05",
        month = "May",
        time = "21:45",
        handledBy = "Marci",
        estimatedDuration = 45,
        location = LocationUiModel(
            name = "Location Name",
            address = AddressUiModel(
                name = "15, Street Name",
                additionalDirections = "Right, first left",
            ),
            phone = "555-333",
            imageUrl = null,
        ),
        conclusion = "Conclusion",
        status = AppointmentStatusUiModel.COMPLETED,
        rating = AppointmentRatingUiModel(emptyImmutableList()),
    ),
    AppointmentUiModel(
        id = "3",
        name = "Third Appointment",
        day = "25",
        month = "February",
        time = "22:45",
        handledBy = "Marci",
        estimatedDuration = 45,
        location = LocationUiModel(
            name = "Location Name",
            address = AddressUiModel(
                name = "15, Street Name",
                additionalDirections = "Right, first left",
            ),
            phone = "555-333",
            imageUrl = null,
        ),
        conclusion = "Conclusion",
        status = AppointmentStatusUiModel.SCHEDULED,
        rating = AppointmentRatingUiModel(emptyImmutableList()),
    ),
    AppointmentUiModel(
        id = "4",
        name = "Fourth Appointment",
        day = "10",
        month = "February",
        time = "20:45",
        handledBy = "Marci",
        estimatedDuration = 45,
        location = LocationUiModel(
            name = "Location Name",
            address = AddressUiModel(
                name = "15, Street Name",
                additionalDirections = "Right, first left",
            ),
            phone = "555-333",
            imageUrl = null,
        ),
        conclusion = null,
        status = AppointmentStatusUiModel.CANCELED,
        rating = AppointmentRatingUiModel(emptyImmutableList()),
    ),
)
