package com.mycare.feature.history.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.operation.pop
import com.bumble.appyx.components.backstack.operation.push
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.composable.AppyxNavigationContainer
import com.bumble.appyx.navigation.modality.NodeContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.node
import com.mycare.core.ui.util.BottomBarHandler
import com.mycare.core.ui.util.InsetsHandler
import com.mycare.feature.history.presentation.HistoryScreen
import com.mycare.feature.history.presentation.HistoryViewModel
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class HistoryNode(
    nodeContext: NodeContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        model = BackStackModel(
            initialTarget = NavTarget.History,
            savedStateMap = nodeContext.savedStateMap,
        ),
        visualisation = { BackStackFader(it) },
    ),
    private val navigateToAppointmentDetails: (NodeContext, String, () -> Unit) -> Node<*>,
) : Node<HistoryNode.NavTarget>(
    appyxComponent = backStack,
    nodeContext = nodeContext,
),
    KoinComponent {

    private val bottomBarHandler: BottomBarHandler = get<BottomBarHandler>()
    private val insetsHandler: InsetsHandler = get<InsetsHandler>()

    sealed interface NavTarget {
        data object History : NavTarget
        data class AppointmentDetails(val id: String) : NavTarget
    }

    @Composable
    override fun Content(modifier: Modifier) {
        Scaffold(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
        ) { paddingValues ->
            AppyxNavigationContainer(
                modifier = Modifier
                    .padding(paddingValues)
                    .consumeWindowInsets(paddingValues)
                    .fillMaxSize(),
                appyxComponent = backStack,
            )
        }
    }

    override fun buildChildNode(navTarget: NavTarget, nodeContext: NodeContext): Node<*> =
        when (navTarget) {
            NavTarget.History -> node(nodeContext) {
                val viewModel = koinInject<HistoryViewModel>()
                val state by viewModel.state.collectAsState()
                val coroutineScope = rememberCoroutineScope()
                coroutineScope.launch { bottomBarHandler.emit(true) }
                coroutineScope.launch { insetsHandler.emit(true) }
                HistoryScreen(
                    state = state,
                    onViewAction = viewModel::onViewAction,
                    navigateToAppointmentDetails = { backStack.push(NavTarget.AppointmentDetails(it)) },
                )
            }

            is NavTarget.AppointmentDetails -> navigateToAppointmentDetails(
                nodeContext,
                navTarget.id,
                backStack::pop,
            )
        }
}
