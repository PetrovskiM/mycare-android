package com.mycare.feature.history.presentation

import com.mycare.core.common.base.BaseViewModel
import com.mycare.core.common.base.ErrorState
import com.mycare.core.common.domain.onFailure
import com.mycare.core.common.domain.onSuccess
import com.mycare.feature.history.domain.HistoryRepository
import com.mycare.feature.history.presentation.contract.HistoryState
import com.mycare.feature.history.presentation.contract.HistoryViewAction
import com.mycare.feature.history.presentation.contract.HistoryViewAction.FiltersCanceled
import com.mycare.feature.history.presentation.contract.HistoryViewAction.FiltersSaved
import com.mycare.feature.history.presentation.contract.HistoryViewAction.OpenFilterBottomSheet
import com.mycare.feature.history.presentation.contract.HistoryViewAction.Retry
import com.mycare.feature.history.presentation.contract.HistoryViewAction.Search
import com.mycare.feature.history.presentation.contract.HistoryViewAction.SearchQueryChanged
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import mycare.feature.history.generated.resources.Res
import mycare.feature.history.generated.resources.history_error_message
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.annotation.Single

@OptIn(ExperimentalResourceApi::class)
@Single // TODO Track https://github.com/InsertKoinIO/koin/issues/1826
internal class HistoryViewModel(
    private val historyRepository: HistoryRepository,
) : BaseViewModel<HistoryState, HistoryViewAction>() {

    init {
        observeSearchQueryChanges()
    }

    private fun getAppointments() {
        updateState { state -> state.copy(isLoading = true) }
        launch {
            historyRepository.getAppointments(
                name = currentState.searchQuery,
            ).onSuccess {
                updateState { state ->
                    state.copy(
                        isLoading = false,
                        appointments = it.toUiModels(),
                    )
                }
            }
                .onFailure(::handleError)
        }
    }

    override fun setInitialState(): HistoryState = HistoryState()

    override fun onViewAction(viewAction: HistoryViewAction) {
        when (viewAction) {
            is OpenFilterBottomSheet -> updateState { it.copy(isFiltersBottomSheetShown = true) }
            FiltersCanceled -> updateState { it.copy(isFiltersBottomSheetShown = false) }
            is SearchQueryChanged -> updateState { state -> state.copy(searchQuery = viewAction.query) }
            FiltersSaved, Retry, Search -> getAppointments()
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQueryChanges() {
        launch {
            state.map { it.searchQuery }
                .distinctUntilChanged()
                .debounce(SEARCH_QUERY_DELAY_DURATION)
                .onEach { getAppointments() }
                .collect()
        }
    }

    override fun handleError(throwable: Throwable) {
        updateState { state ->
            state.copy(
                isLoading = false,
                error = ErrorState.OnScreen(Res.string.history_error_message),
            )
        }
    }
}

private const val SEARCH_QUERY_DELAY_DURATION = 500L
