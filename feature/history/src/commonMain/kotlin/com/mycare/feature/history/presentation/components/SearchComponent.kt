package com.mycare.feature.history.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mycare.core.ui.components.MCText
import com.mycare.core.ui.theme.Dimens.Space
import mycare.feature.history.generated.resources.Res
import mycare.feature.history.generated.resources.history_clear_cd
import mycare.feature.history.generated.resources.history_search_hint
import mycare.feature.history.generated.resources.ic_clear
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
internal fun SearchComponent(
    query: String,
    queryChanged: (String) -> Unit,
) {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Space),
        query = query,
        onQueryChange = queryChanged,
        onSearch = queryChanged,
        active = false,
        onActiveChange = {},
        placeholder = { MCText.BodySmall(text = Res.string.history_search_hint) },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { queryChanged("") }) {
                    Icon(
                        painter = painterResource(resource = Res.drawable.ic_clear),
                        contentDescription = stringResource(resource = Res.string.history_clear_cd),
                    )
                }
            }
        },
    ) {}
}
