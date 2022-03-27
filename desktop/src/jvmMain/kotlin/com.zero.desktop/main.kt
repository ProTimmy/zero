package com.zero.desktop

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import com.badoo.reaktive.coroutinesinterop.asScheduler
import com.badoo.reaktive.scheduler.overrideSchedulers
import com.zero.common.root.RootComponent
import com.zero.common.root.RootComponent.Child.Loading
import com.zero.common.root.RootComponent.Child.Screen
import com.zero.common.root.RootController
import com.zero.components.LoadingComponent
import com.zero.components.ScreenComponent
import com.zero.models.core.ButtonModel
import com.zero.models.core.TextModel
import com.zero.models.layouts.ColumnModel
import com.zero.models.layouts.RowModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalDecomposeApi::class)
fun main() {
    overrideSchedulers(main = Dispatchers.Main::asScheduler)

    val lifecycle = LifecycleRegistry()
    val rootComponent = createRootComponent(DefaultComponentContext(lifecycle = lifecycle))
    val id = rootComponent.storeDemoScreen()
    rootComponent.getScreenById(id)

    application {
        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Zero"
        ) {
            MainContent(rootComponent)
        }
    }
}

private fun createRootComponent(componentContext: ComponentContext): RootComponent =
    RootController(
        componentContext = componentContext,
        storeFactory = LoggingStoreFactory(TimeTravelStoreFactory()),
    )

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun MainContent(rootComponent: RootComponent) {
    Children(routerState = rootComponent.routerState, animation = crossfadeScale()) {
        when (val child = it.instance) {
            is Loading -> LoadingComponent()
            is Screen -> ScreenComponent(child.component)
        }
    }
}

private val DEMO_COMPONENTS =
    listOf(
        ColumnModel(
            id = "5e6a6352-488a-4d79-a484-44c87974b1cc",
            isRoot = true,
            childComponents = listOf(
                "8e43a908-9b41-4514-aa7a-808da599b092",
                "04925e7e-8563-44aa-8317-910d80b59173",
                "192468bd-9f59-45cf-afba-819da2efab14",
                "fac2aa94-28f4-4885-b748-089edc495a73",
            )
        ),
        RowModel(
            id = "8e43a908-9b41-4514-aa7a-808da599b092",
            childComponents = listOf(
                "3f360385-2206-400c-9461-73abf3609a37",
                "e022e42d-e792-490d-8fca-41f191a3265c",
            )
        ),
        RowModel(
            id = "04925e7e-8563-44aa-8317-910d80b59173",
            childComponents = listOf(
                "769d7487-fd0d-4df3-b5de-53791c3af63a",
                "a5cad7cb-2fff-4510-bbf6-0ea1f252bdfd",
            )
        ),
        RowModel(
            id = "192468bd-9f59-45cf-afba-819da2efab14",
            childComponents = listOf(
                "fac2aa94-28f4-4885-b748-8590ea84cf97",
                "f6f51f4a-5fa7-4ab6-a9f2-089edc495a73",
            )
        ),
        TextModel(
            id = "3f360385-2206-400c-9461-73abf3609a37",
            text = "Hello ",
        ),
        TextModel(
            id = "e022e42d-e792-490d-8fca-41f191a3265c",
            text = "world!",
        ),
        TextModel(
            id = "769d7487-fd0d-4df3-b5de-53791c3af63a",
            text = "Test ",
        ),
        TextModel(
            id = "a5cad7cb-2fff-4510-bbf6-0ea1f252bdfd",
            text = "1",
        ),
        TextModel(
            id = "fac2aa94-28f4-4885-b748-8590ea84cf97",
            text = "Counter: ",
        ),
        TextModel(
            id = "f6f51f4a-5fa7-4ab6-a9f2-089edc495a73",
            text = "0",
        ),
        ButtonModel(
            id = "fac2aa94-28f4-4885-b748-089edc495a73",
        ),
    )
