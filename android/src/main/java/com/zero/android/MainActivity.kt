package com.zero.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import com.zero.common.root.RootComponent
import com.zero.common.root.RootComponent.Child.Loading
import com.zero.common.root.RootComponent.Child.Screen
import com.zero.common.root.RootController
import com.zero.components.LoadingComponent
import com.zero.components.ScreenComponent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = createRootComponent(defaultComponentContext())
        val id = rootComponent.storeDemoScreen()
        rootComponent.getScreenById(id)

        setContent {
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
