package com.mycare.feature.authentication.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AuthenticationScreen(navigateToTreatments: () -> Unit) {
    AuthenticationContent(navigateToTreatments = navigateToTreatments)
}

@Composable
private fun AuthenticationContent(navigateToTreatments: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = navigateToTreatments) {
            Text("Login")
        }
    }
}