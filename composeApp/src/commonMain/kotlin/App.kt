import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mycare.core.ui.extension.collectAsEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun RootScreen(
    rootViewModel: RootViewModel = koinInject(),
    navigateToLoggedIn: () -> Unit,
    navigateToLoggedOut: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text("ROOT")
    }
    navigateToLoggedIn()
    rootViewModel.loggedIn.collectAsEffect {
        if (it) {
            navigateToLoggedIn()
        } else {
            navigateToLoggedOut()
        }
    }
}

class RootViewModel {

    val loggedIn = MutableSharedFlow<Boolean>()

    init {
        CoroutineScope(Dispatchers.Default).launch {
            loggedIn.emit(true)
        }
    }
}