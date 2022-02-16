package com.zero.desktop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.zero.common.CommonAction
import com.zero.common.CommonViewModel
import com.zero.components.ComponentComposer
import com.zero.models.core.ScreenModel
import com.zero.models.core.TextModel
import com.zero.models.layouts.ColumnModel
import com.zero.models.layouts.RowModel

fun main() = application {
	val windowState = rememberWindowState()

	val viewModel = CommonViewModel()

	Window(
		onCloseRequest = ::exitApplication,
		state = windowState,
		title = "Zero"
	) {
		MainContent(viewModel)
	}
}

private fun initDemo(viewModel: CommonViewModel) {
	val mockScreenModel = ScreenModel(
		content = listOf(
			ColumnModel(
				content = listOf(
					RowModel(
						content = listOf(
							TextModel(text = "Hello"),
							TextModel(text = "world!")
						)
					),
					RowModel(
						content = listOf(
							TextModel(text = "Test"),
							TextModel(text = "1")
						)
					),
					RowModel(
						content = listOf(
							TextModel(text = "Test"),
							TextModel(text = "2")
						)
					),
				)
			)
		)
	)
	viewModel.dispatch(CommonAction.Init(mockScreenModel))
}

@Composable
fun MainContent(viewModel: CommonViewModel) {
	val viewState = viewModel.observeState().collectAsState()

	ComponentComposer(viewState.value.screenModel)
}
