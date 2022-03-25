package com.zero.common.root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.zero.models.ComponentModel

interface RootComponent {

    val routerState: Value<RouterState<*, Child>>

    fun getScreenById(id: String)

    // TODO: Remove Test Functions
    fun storeScreen(componentModels: List<ComponentModel>): String

    sealed class Child {
        object Loading : Child()

        data class Screen(val component: com.zero.common.screen.Screen) : Child()
    }
}
