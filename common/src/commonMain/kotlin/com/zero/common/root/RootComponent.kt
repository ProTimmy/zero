package com.zero.common.root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value

interface RootComponent {

    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        data class Screen(val component: com.zero.common.screen.Screen) : Child()
    }
}
