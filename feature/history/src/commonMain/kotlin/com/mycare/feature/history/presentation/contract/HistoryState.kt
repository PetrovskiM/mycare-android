package com.mycare.feature.history.presentation.contract

import com.mycare.core.common.base.BaseState
import com.mycare.core.common.base.ErrorState
import com.mycare.core.ui.util.ImmutableList
import com.mycare.core.ui.util.emptyImmutableList
import com.mycare.feature.history.presentation.model.HistoryUiModel

internal data class HistoryState(
    override val isLoading: Boolean = true,
    override val error: ErrorState? = null,
    val appointments: ImmutableList<HistoryUiModel> = emptyImmutableList(),
    val searchQuery: String = "",
    val isFiltersBottomSheetShown: Boolean = false,
    val hasSelectedFilters: Boolean = false,
) : BaseState
