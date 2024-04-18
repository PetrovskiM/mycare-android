package com.mycare.navigation

import RootScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.operation.replace
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.composable.AppyxNavigationContainer
import com.bumble.appyx.navigation.modality.NodeContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.node
import com.mycare.core.ui.util.InsetsHandler
import com.mycare.navigation.RootNode.NavTarget.LoggedIn
import com.mycare.navigation.RootNode.NavTarget.LoggedOut
import com.mycare.navigation.RootNode.NavTarget.Root
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

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
),
    KoinComponent {

    private val insetsHandler: InsetsHandler = get<InsetsHandler>()

    sealed interface NavTarget {
        data object Root : NavTarget
        data object LoggedOut : NavTarget
        data object LoggedIn : NavTarget
    }

    @Composable
    override fun Content(modifier: Modifier) {
        val shouldInsetTop by insetsHandler.shouldInsetTop.collectAsState()
        Scaffold(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
        ) { paddingValues ->
            AppyxNavigationContainer(
                modifier = Modifier
                    .padding(
                        start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
                        end = paddingValues.calculateEndPadding(LocalLayoutDirection.current),
                        bottom = paddingValues.calculateBottomPadding(),
                        top = if (shouldInsetTop) paddingValues.calculateTopPadding() else 0.dp,
                    )
                    .consumeWindowInsets(paddingValues)
                    .fillMaxSize(),
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
