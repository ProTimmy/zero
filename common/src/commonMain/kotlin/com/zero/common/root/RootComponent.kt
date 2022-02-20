package com.zero.common.root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.zero.common.screen.ScreenComponent

interface RootComponent {

    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        data class Screen(val component: ScreenComponent) : Child()
    }
}
