package com.mycare.feature.appointments.navigation

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
import com.bumble.appyx.components.backstack.operation.push
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.composable.AppyxNavigationContainer
import com.bumble.appyx.navigation.modality.NodeContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.node
import com.mycare.core.ui.util.BottomBarHandler
import com.mycare.feature.appointments.details.presentation.AppointmentDetailsScreen
import com.mycare.feature.appointments.navigation.AppointmentsNode.NavTarget.AppointmentDetails
import com.mycare.feature.appointments.navigation.AppointmentsNode.NavTarget.Appointments
import com.mycare.feature.appointments.presentation.AppointmentsScreen
import com.mycare.feature.appointments.presentation.AppointmentsViewModel
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class AppointmentsNode(
    nodeContext: NodeContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        model = BackStackModel(
            initialTarget = Appointments,
            savedStateMap = nodeContext.savedStateMap,
        ),
        visualisation = { BackStackFader(it) },
    ),
    private val navigateToLoggedOut: () -> Unit,
) : Node<AppointmentsNode.NavTarget>(
    appyxComponent = backStack,
    nodeContext = nodeContext,
), KoinComponent {

    private val bottomBarHandler: BottomBarHandler = get<BottomBarHandler>()

    sealed interface NavTarget {
        data object Appointments : NavTarget
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
            Appointments -> node(nodeContext) {
                val coroutineScope = rememberCoroutineScope()
                coroutineScope.launch { bottomBarHandler.emit(true) }

                val viewModel = koinInject<AppointmentsViewModel>()
                val state by viewModel.state.collectAsState()
                AppointmentsScreen(
                    state = state,
                    navigateToAppointmentDetails = { backStack.push(AppointmentDetails(id = it)) },
                )
            }

            is AppointmentDetails -> node(nodeContext) {
                val coroutineScope = rememberCoroutineScope()
                coroutineScope.launch { bottomBarHandler.emit(false) }
                AppointmentDetailsScreen(
                    viewModel = koinInject { parametersOf(navTarget.id) },
                )
            }
        }
}

/*
class BottomBarHook : Interactor<AppointmentDetailsNode>() {
   // val bottomBarVisibilities = Stack<Boolean>()
    override fun onCreate(lifecycle: Lifecycle) {
        println("TESTER ATTACHED onCreate")
        lifecycle.subscribe(
            onCreate = {
                println("TESTER ATTACHED subscribe")
                // This lambda is executed every time a node of type Child1Node is attached:
                whenChildAttached { commonLifecycle: Lifecycle, child1: AppointmentDetailsNode ->
                    println("TESTER ATTACHED whenChildAttached")
                }
            },
            onResume = {
                println("TESTER ATTACHED onResume")
            },
            onStart = { println("TESTER ATTACHED onStart") },
        )
    }
}

class AppointmentDetailsNode(
    nodeContext: NodeContext,
    plugins: List<Plugin>,
    private val appointmentId: String,
) : LeafNode(
    nodeContext = nodeContext,
    plugins = plugins,
) {
    @Composable
    override fun Content(modifier: Modifier) {

    }
}*/
