package com.mycare.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mycare.core.ui.components.MCText.BodyMedium
import com.mycare.core.ui.components.MCText.HeadlineSmall
import com.mycare.core.ui.theme.Dimens

@Composable
fun DateCardComponent(
    backgroundColor: Color,
    day: String,
    month: String,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimens.Elevation.Large),
    ) {
        Column(
            modifier = Modifier
                .widthIn(min = DATE_MIN_WIDTH.dp)
                .padding(all = Dimens.Space),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeadlineSmall(
                text = day,
                textColor = MaterialTheme.colorScheme.onPrimary,
            )
            BodyMedium(
                text = month,
                textColor = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

private const val DATE_MIN_WIDTH = 90
