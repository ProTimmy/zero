package com.zero.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.zero.common.CommonAction
import com.zero.common.CommonViewModel
import com.zero.components.ComponentComposer

class MainActivity : AppCompatActivity() {
    private val viewModel = CommonViewModel()

    // 	private val mockScreenModel = ScreenModel(
    // 		content = listOf(
    // 			ColumnModel(
    // 				content = listOf(
    // 					RowModel(
    // 						content = listOf(
    // 							TextModel(text = "Hello"),
    // 							TextModel(text = "world!")
    // 						)
    // 					),
    // 					RowModel(
    // 						content = listOf(
    // 							TextModel(text = "Test"),
    // 							TextModel(text = "1")
    // 						)
    // 					),
    // 					RowModel(
    // 						content = listOf(
    // 							TextModel(text = "Test"),
    // 							TextModel(text = "2")
    // 						)
    // 					),
    // 				)
    // 			)
    // 		)
    // 	)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.dispatch(CommonAction.Init(mockScreenModel))

        setContent { MainView(viewModel = viewModel) }
    }
}

@Composable
fun MainView(viewModel: CommonViewModel) {
    val state = viewModel.observeState().collectAsState()

    ComponentComposer(model = state.value.screenModel)
}
