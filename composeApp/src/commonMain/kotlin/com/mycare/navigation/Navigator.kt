package com.mycare.navigation

import androidx.compose.runtime.compositionLocalOf
import com.bumble.appyx.navigation.plugin.NodeReadyObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Suppress("CompositionLocalAllowlist")
val LocalNavigator = compositionLocalOf { Navigator() }

class Navigator : NodeReadyObserver<RootNode> {

    private lateinit var rootNode: RootNode
    private lateinit var lifecycleScope: CoroutineScope

    override fun init(node: RootNode) {
        rootNode = node
        lifecycleScope = node.lifecycleScope
    }

    fun goToAuthentication() {
        /*lifecycleScope.launch {
            rootNode
                .waitForMainAttached()
                .goToCakes(delay = 500)
        }*/
    }

    fun goToAppointments() {
        lifecycleScope.launch {
            rootNode
        }
    }
}