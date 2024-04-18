package com.mycare.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingIndicator(
    modifier: Modifier? = null,
    size: Dp = LoadingIndicatorSize,
    strokeWidth: Dp? = null
) {
    Box(
        modifier = modifier ?: Modifier.fillMaxSize(),
        contentAlignment = Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(size),
            strokeWidth = strokeWidth ?: ProgressIndicatorDefaults.CircularStrokeWidth
        )
    }
}

private val LoadingIndicatorSize = 48.dp