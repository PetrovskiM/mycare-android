package com.mycare.feature.history.presentation

import com.mycare.core.common.base.BaseViewModel
import com.mycare.feature.history.presentation.contract.HistoryState
import com.mycare.feature.history.presentation.contract.HistoryViewAction
import com.mycare.feature.history.presentation.contract.HistoryViewAction.FiltersCanceled
import com.mycare.feature.history.presentation.contract.HistoryViewAction.FiltersSaved
import com.mycare.feature.history.presentation.contract.HistoryViewAction.OpenFilterBottomSheet
import com.mycare.feature.history.presentation.contract.HistoryViewAction.SearchQueryChanged
import org.koin.core.annotation.Single

@Single
internal class HistoryViewModel : BaseViewModel<HistoryState, HistoryViewAction>() {

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
