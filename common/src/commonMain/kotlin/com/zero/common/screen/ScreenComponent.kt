package com.zero.common.screen

import arrow.core.Option
import com.arkivanov.decompose.value.Value
import com.zero.models.ComponentModel

interface ScreenComponent {
    val model: Value<Model>

    data class Model(
        val counter: Int = 0,
        val components: HashMap<String, ComponentModel>,
        val rootComponents: List<String>,
    )

    fun initScreen(components: HashMap<String, ComponentModel>, rootComponents: List<String>)

    fun updateModel(componentId: String) // TODO: Implement real functionality

    fun getComponentModel(id: String): Option<ComponentModel>

    sealed class Output
}
