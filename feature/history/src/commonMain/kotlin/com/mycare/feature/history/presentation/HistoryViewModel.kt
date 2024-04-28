package com.mycare.feature.history.presentation

import com.mycare.core.common.base.BaseViewModel
import com.mycare.core.common.domain.onFailure
import com.mycare.core.common.domain.onSuccess
import com.mycare.feature.history.domain.HistoryRepository
import com.mycare.feature.history.presentation.contract.HistoryState
import com.mycare.feature.history.presentation.contract.HistoryViewAction
import com.mycare.feature.history.presentation.contract.HistoryViewAction.FiltersCanceled
import com.mycare.feature.history.presentation.contract.HistoryViewAction.FiltersSaved
import com.mycare.feature.history.presentation.contract.HistoryViewAction.OpenFilterBottomSheet
import com.mycare.feature.history.presentation.contract.HistoryViewAction.SearchQueryChanged
import org.koin.core.annotation.Single

@Single // TODO Track https://github.com/InsertKoinIO/koin/issues/1826
internal class HistoryViewModel(
    private val historyRepository: HistoryRepository,
) : BaseViewModel<HistoryState, HistoryViewAction>() {

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        launch {
            historyRepository.getAppointments()
                .onSuccess { it.toUiModels() }
                .onFailure(::handleError)
        }
    }

    override fun setInitialState(): HistoryState = HistoryState()

    override fun onViewAction(viewAction: HistoryViewAction) {
        when (viewAction) {
            is OpenFilterBottomSheet -> updateState { it.copy(isFiltersBottomSheetShown = true) }
            FiltersCanceled -> updateState { it.copy(isFiltersBottomSheetShown = false) }
            FiltersSaved -> TODO()
            is SearchQueryChanged -> updateState { state -> state.copy(searchQuery = viewAction.query) }
        }
    }

    override fun handleError(throwable: Throwable) {
        TODO("Not yet implemented")
    }
}
