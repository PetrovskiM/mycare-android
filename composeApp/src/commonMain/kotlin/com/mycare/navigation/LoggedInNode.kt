package com.mycare.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import com.bumble.appyx.components.spotlight.Spotlight
import com.bumble.appyx.components.spotlight.SpotlightModel
import com.bumble.appyx.components.spotlight.operation.activate
import com.bumble.appyx.components.spotlight.ui.fader.SpotlightFader
import com.bumble.appyx.navigation.composable.AppyxNavigationContainer
import com.bumble.appyx.navigation.modality.NodeContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.node
import com.bumble.appyx.navigation.plugin.BackPressHandler
import com.mycare.core.ui.util.BottomBarHandler
import com.mycare.core.ui.util.InsetsHandler
import com.mycare.feature.appointments.navigation.AppointmentsNode
import com.mycare.feature.appointments.navigation.appointmentDetailsNode
import com.mycare.feature.history.navigation.HistoryNode
import com.mycare.navigation.LoggedInNode.NavTarget.Appointments
import com.mycare.navigation.LoggedInNode.NavTarget.Authentication
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class LoggedInNode(
    nodeContext: NodeContext,
    private val spotlight: Spotlight<NavTarget> = Spotlight(
        model = SpotlightModel(
            items = BottomBarItems.entries.map { it.target },
            initialActiveIndex = 0f,
            savedStateMap = nodeContext.savedStateMap,
        ),
        visualisation = { SpotlightFader(it) },
    ),
    private val navigateToLoggedOut: () -> Unit,
) : Node<LoggedInNode.NavTarget>(
    appyxComponent = spotlight,
    nodeContext = nodeContext,
    plugins = listOf(Back()),
),
    KoinComponent {

    private val bottomBarHandler: BottomBarHandler = get<BottomBarHandler>()
    private val insetsHandler: InsetsHandler = get<InsetsHandler>()

    sealed interface NavTarget {
        data object Appointments : NavTarget
        data object History : NavTarget
        data object Trials : NavTarget
        data object More : NavTarget
        data object Authentication : NavTarget
    }

    @Composable
    override fun Content(modifier: Modifier) {
        val isBottomBarVisible by bottomBarHandler.isBottomBarVisible.collectAsState()
        val selectedBottomTab by spotlight.activeIndex.collectAsState()
        val shouldInsetTop by insetsHandler.shouldInsetTop.collectAsState()
        Scaffold(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            bottomBar = {
                AnimatedVisibility(visible = isBottomBarVisible) {
                    BottomNavigationBar(
                        spotlight = spotlight,
                        selectedBottomTab = selectedBottomTab.toInt(),
                    )
                }
            },
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
                appyxComponent = spotlight,
            )
        }
    }

    override fun buildChildNode(navTarget: NavTarget, nodeContext: NodeContext): Node<*> =
        when (navTarget) {
            Appointments -> AppointmentsNode(
                nodeContext = nodeContext,
                navigateToLoggedOut = navigateToLoggedOut,
            )

            Authentication -> LoggedOutNode(
                nodeContext = nodeContext,
            )

            NavTarget.More -> node(nodeContext) {
                Text("More")
            }

            NavTarget.History -> HistoryNode(
                nodeContext = nodeContext,
                navigateToAppointmentDetails = { context, id, navigateBack ->
                    appointmentDetailsNode(
                        nodeContext = context,
                        bottomBarHandler = bottomBarHandler,
                        insetsHandler = insetsHandler,
                        appointmentId = id,
                        navigateBack = navigateBack,
                    )
                },
            )

            NavTarget.Trials -> node(nodeContext) {
                Text("Trials")
            }
        }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun BottomNavigationBar(
    spotlight: Spotlight<LoggedInNode.NavTarget>,
    selectedBottomTab: Int,
) {
    NavigationBar {
        BottomBarItems.entries.forEachIndexed { index, item ->
            val isSelected = selectedBottomTab == index
            NavigationBarItem(
                selected = isSelected,
                onClick = { spotlight.activate(index.toFloat()) },
                icon = {
                    Icon(
                        painter = painterResource(
                            if (isSelected) item.selectedRes else item.unselectedRes,
                        ),
                        contentDescription = null,
                    )
                },
                label = {
                    Text(stringResource(item.labelRes))
                },
            )
        }
    }
}

class Back : BackPressHandler {
    override val onBackPressedCallback: BackPressHandler.OnBackPressedCallback? = object :
        BackPressHandler.OnBackPressedCallback {
        override val isEnabled: Boolean
            get() = true

        override fun handleOnBackPressed() {
            println("TESTER")
        }
    }

    override fun handleOnBackPressed(): Boolean {
        println("TESTER")
        return super.handleOnBackPressed()
    }
}
