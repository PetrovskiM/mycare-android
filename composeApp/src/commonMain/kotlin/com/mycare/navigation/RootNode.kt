package com.mycare.navigation

import RootScreen
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
import com.mycare.navigation.RootNode.NavTarget.LoggedIn
import com.mycare.navigation.RootNode.NavTarget.LoggedOut
import com.mycare.navigation.RootNode.NavTarget.Root

class RootNode(
    nodeContext: NodeContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        model = BackStackModel(
            initialTarget = Root,
            savedStateMap = nodeContext.savedStateMap,
        ),
        visualisation = { BackStackFader(it) },
    ),
) : Node<RootNode.NavTarget>(
    appyxComponent = backStack,
    nodeContext = nodeContext,
) {

    sealed interface NavTarget {
        data object Root : NavTarget
        data object LoggedOut : NavTarget
        data object LoggedIn : NavTarget
    }

    @Composable
    override fun Content(modifier: Modifier) {
        Scaffold(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
        ) {
            AppyxNavigationContainer(
                modifier = Modifier.fillMaxSize(),
                appyxComponent = backStack,
            )
        }
    }

    override fun buildChildNode(navTarget: NavTarget, nodeContext: NodeContext): Node<*> =
        when (navTarget) {
            Root -> node(nodeContext) {
                RootScreen(
                    navigateToLoggedIn = { backStack.replace(LoggedIn) },
                    navigateToLoggedOut = { backStack.replace(LoggedOut) },
                )
            }

            LoggedIn -> LoggedInNode(
                nodeContext = nodeContext,
                navigateToLoggedOut = { backStack.replace(LoggedOut) },
            )

            LoggedOut -> LoggedOutNode(
                nodeContext = nodeContext,
            )
        }
}
