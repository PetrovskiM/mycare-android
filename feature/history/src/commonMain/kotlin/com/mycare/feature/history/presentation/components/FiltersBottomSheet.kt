package com.mycare.feature.history.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mycare.core.ui.components.MCText.BodyLarge
import com.mycare.core.ui.components.MCText.TitleLarge
import com.mycare.core.ui.components.Spacer
import com.mycare.core.ui.theme.Dimens.Space
import com.mycare.core.ui.theme.Dimens.SpaceMedium
import com.mycare.core.ui.theme.Dimens.SpaceSmall
import mycare.feature.history.generated.resources.Res
import mycare.feature.history.generated.resources.history_filter
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
internal fun FiltersBottomSheet(
    onDismiss: () -> Unit,
    onFilterSaved: () -> Unit,
    onFilterSelected: () -> Unit,
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
        ) {
            TitleLarge(
                modifier = Modifier.padding(
                    bottom = Space,
                    end = Space,
                    start = Space,
                ),
                text = "Filter",
            )
            HorizontalDivider()
            Spacer(height = SpaceMedium)
            Column(
                modifier = Modifier
                    .padding(Space)
                    .weight(1f),
            ) {
                TitleLarge(text = "Rating")
                Spacer(height = SpaceMedium)
                Spacer(height = SpaceSmall)
                FilterOptionComponent(true, onFilterSelected)
                FilterOptionComponent(false, onFilterSelected)
            }
            Spacer(width = Space)
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = Space),
                onClick = onFilterSaved,
            ) {
                BodyLarge(text = Res.string.history_filter)
            }
        }
    }
}

@Composable
private fun FilterOptionComponent(
    shouldShowDivider: Boolean,
    onFilterSelected: () -> Unit,
) {
    Column(modifier = Modifier.padding(top = SpaceSmall)) {
        Row(
            modifier = Modifier.clickable(onClick = onFilterSelected),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BodyLarge(
                text = "Very long option name that goes beyond a line",
                modifier = Modifier.weight(1f),
            )
            Checkbox(
                checked = false,
                onCheckedChange = {},
            )
        }
        if (shouldShowDivider) {
            Spacer(height = SpaceSmall)
            HorizontalDivider()
        }
    }
}
