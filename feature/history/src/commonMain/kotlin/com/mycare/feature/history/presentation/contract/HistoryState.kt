package com.mycare.feature.history.presentation.contract

import com.mycare.core.common.base.BaseState
import com.mycare.core.common.base.ErrorState

internal data class HistoryState(
    override val isLoading: Boolean = false,
    override val error: ErrorState? = null,
    val searchQuery: String = "",
    val isFiltersBottomSheetShown: Boolean = false,
    val hasSelectedFilters: Boolean = false,
) : BaseState
