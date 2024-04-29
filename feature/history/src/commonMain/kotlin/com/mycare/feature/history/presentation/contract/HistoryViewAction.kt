package com.mycare.feature.history.presentation.contract

internal sealed interface HistoryViewAction {
    data class SearchQueryChanged(val query: String) : HistoryViewAction
    data class OpenFilterBottomSheet(val filter: String) : HistoryViewAction
    data object FiltersSaved : HistoryViewAction
    data object FiltersCanceled : HistoryViewAction
    data object Retry : HistoryViewAction
    data object Search : HistoryViewAction
}
