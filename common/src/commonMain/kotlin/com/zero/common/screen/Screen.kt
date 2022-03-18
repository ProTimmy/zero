package com.zero.common.screen

import com.arkivanov.decompose.value.Value
import com.zero.common.component.Component
import com.zero.models.ComponentModel

interface Screen {
    val model: Value<Model>

    data class Model(
        val components: HashMap<String, Component>,
        val rootComponents: List<String>,
    )

    fun initDemo()

    fun initScreen(componentModels: List<ComponentModel>, rootComponents: List<String>)

    fun updateModel(componentId: String) // TODO: Implement real functionality

    fun getComponentModel(id: String): Component?
}
