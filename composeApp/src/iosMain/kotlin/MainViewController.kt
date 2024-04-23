import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.ComposeUIViewController
import androidx.compose.ui.zIndex
import com.bumble.appyx.navigation.integration.IosNodeHost
import com.bumble.appyx.navigation.integration.MainIntegrationPoint
import com.mycare.core.network.di.networkModule
import com.mycare.core.ui.di.CoreUiModule
import com.mycare.core.ui.theme.AppTheme
import com.mycare.di.mainModule
import com.mycare.feature.appointments.di.AppointmentsModule
import com.mycare.feature.history.di.HistoryModule
import com.mycare.navigation.RootNode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

val backEvents: Channel<Unit> = Channel()
private val integrationPoint = MainIntegrationPoint()

@Suppress("FunctionName")
fun MainViewController() = ComposeUIViewController {
    AppTheme {
        IosNodeHost(
            modifier = Modifier,
            // See back handling section in the docs below!
            onBackPressedEvents = backEvents.receiveAsFlow(),
            integrationPoint = integrationPoint,
        ) {
            RootNode(
                nodeContext = it,
            )
        }
    }
}

@Composable
private fun BackButton(coroutineScope: CoroutineScope) {
    IconButton(
        onClick = {
            coroutineScope.launch {
                backEvents.send(Unit)
            }
        },
        modifier = Modifier.zIndex(99f),
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            tint = Color.White,
            contentDescription = "Go Back",
        )
    }
}

fun initKoin() {
    startKoin {
        modules(
            mainModule,
            AppointmentsModule().module,
            CoreUiModule().module,
            networkModule,
            HistoryModule().module,
        )
    }
}
