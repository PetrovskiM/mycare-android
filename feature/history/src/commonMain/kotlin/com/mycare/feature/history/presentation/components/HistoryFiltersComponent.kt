@file:OptIn(ExperimentalResourceApi::class)

package com.mycare.feature.history.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mycare.core.ui.components.MCText.BodyMedium
import com.mycare.core.ui.components.Spacer
import com.mycare.core.ui.theme.Dimens.BorderWidth
import com.mycare.core.ui.theme.Dimens.Radius.XLarge
import com.mycare.core.ui.theme.Dimens.Space
import com.mycare.core.ui.theme.Dimens.SpaceMedium
import com.mycare.core.ui.theme.Dimens.SpaceSmall
import com.mycare.core.ui.util.ImmutableList
import com.mycare.feature.history.presentation.model.HistoryFilterUiModel
import mycare.feature.history.generated.resources.Res
import mycare.feature.history.generated.resources.history_all_filters
import mycare.feature.history.generated.resources.ic_down
import mycare.feature.history.generated.resources.ic_filter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun HistoryFiltersComponent(
    filters: ImmutableList<HistoryFilterUiModel>,
    hasSelectedFilters: Boolean,
    onFilterClick: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(horizontal = Space),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Center,
    ) {
        AllFiltersComponent(hasSelectedFilters = hasSelectedFilters)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Center,
        ) {
            items(filters) {
                Spacer(width = SpaceSmall)
                HistoryFilterComponent(
                    filter = it,
                    onFilterClick = onFilterClick,
                )
                Spacer(width = SpaceSmall)
            }
        }
    }
}

@Composable
private fun AllFiltersComponent(hasSelectedFilters: Boolean) {
    Row(
        modifier = Modifier.height(height = ALL_FILTERS_HEIGHT.dp),
        horizontalArrangement = Center,
        verticalAlignment = CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(size = XLarge))
                .background(color = MaterialTheme.colorScheme.background)
                .border(
                    width = BorderWidth.Small,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(size = XLarge),
                )
                .padding(
                    vertical = SpaceMedium,
                    horizontal = SpaceMedium,
                ),
            horizontalArrangement = Center,
            verticalAlignment = CenterVertically,
        ) {
            BadgedBox(
                badge = {
                    if (hasSelectedFilters) {
                        Badge(modifier = Modifier.size(BADGE_SIZE.dp))
                    }
                },
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_filter),
                    contentDescription = null,
                )
            }
            Spacer(width = SpaceSmall)
            BodyMedium(
                text = Res.string.history_all_filters,
                textColor = MaterialTheme.colorScheme.onBackground,
            )
        }
        Spacer(width = SpaceSmall)
        VerticalDivider()
    }
}

@Composable
private fun HistoryFilterComponent(
    filter: HistoryFilterUiModel,
    onFilterClick: () -> Unit,
) {
    val onColor = if (filter.isActive) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onBackground
    }
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = XLarge))
            .background(
                color = if (filter.isActive) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.background
                },
            )
            .border(
                width = BorderWidth.Small,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(size = XLarge),
            )
            .padding(
                vertical = SpaceMedium,
                horizontal = SpaceMedium,
            ).clickable(onClick = onFilterClick),
        contentAlignment = Alignment.Center,
    ) {
        Row(verticalAlignment = CenterVertically) {
            BodyMedium(
                text = filter.name,
                textColor = onColor,
            )
            Spacer(width = SpaceSmall)
            Icon(
                painterResource(resource = Res.drawable.ic_down),
                contentDescription = null,
                tint = onColor,
            )
        }
    }
}

private const val ALL_FILTERS_HEIGHT = 44
private const val BADGE_SIZE = 10
