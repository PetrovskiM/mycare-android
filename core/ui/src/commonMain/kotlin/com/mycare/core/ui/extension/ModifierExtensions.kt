package com.mycare.core.ui.extension

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.fadingEdge(
    canScrollForward: Boolean,
    canScrollBackward: Boolean,
) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithCache {
        val brush = Brush.verticalGradient(
            0.0f to Color.Transparent,
            if (canScrollBackward) 0.1f to Color.Red else 0.01f to Color.Red,
            if (canScrollForward) 0.9f to Color.Red else 0.99f to Color.Red,
            1.0f to Color.Transparent,
        )
        onDrawWithContent {
            drawContent()
            drawRect(brush, blendMode = BlendMode.DstIn)
        }
    }