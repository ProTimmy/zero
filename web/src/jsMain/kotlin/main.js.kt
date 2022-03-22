import androidx.compose.ui.window.Window
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        Window("zero") {
            Text("Hello world!")
        }
    }
}
