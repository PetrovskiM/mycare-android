package com.mycare.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.operation.replace
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.composable.AppyxNavigationContainer
import com.bumble.appyx.navigation.modality.NodeContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.node
import com.mycare.feature.authentication.presentation.AuthenticationScreen
import com.mycare.navigation.LoggedOutNode.NavTarget.Authentication
import com.mycare.navigation.LoggedOutNode.NavTarget.Appointments

class LoggedOutNode(
    nodeContext: NodeContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        model = BackStackModel(
            initialTarget = Authentication,
            savedStateMap = nodeContext.savedStateMap,
        ),
        visualisation = { BackStackFader(it) },
    ),
) : Node<LoggedOutNode.NavTarget>(
    appyxComponent = backStack,
    nodeContext = nodeContext
) {

    sealed interface NavTarget {
        data object Authentication : NavTarget
        data object Appointments : NavTarget
    }

    @Composable
    override fun Content(modifier: Modifier) {
        Scaffold(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
        ) {
            AppyxNavigationContainer(
                appyxComponent = backStack,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }

    override fun buildChildNode(navTarget: NavTarget, nodeContext: NodeContext): Node<*> =
        when (navTarget) {
            Authentication ->
                node(nodeContext) {
                    AuthenticationScreen(
                        navigateToTreatments = { backStack.replace(Appointments) },
                    )
                }

            Appointments -> LoggedInNode(
                nodeContext = nodeContext,
                navigateToLoggedOut = { backStack.replace(Authentication) },
            )
        }
}