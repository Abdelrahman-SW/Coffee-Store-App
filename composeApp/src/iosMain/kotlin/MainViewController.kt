import androidx.compose.ui.window.ComposeUIViewController
import di.initKoin
import ui.App

@Suppress("Cannot access class 'platform.UIKit.UIViewController'. Check your module classpath for missing or conflicting dependencies")
fun MainViewController() = ComposeUIViewController(
    configure = {
         initKoin()
    }
) { App() }