package com.zero.common

import com.zero.models.core.ScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class CommonState(
    val screenModel: ScreenModel = ScreenModel()
) : State

sealed class CommonAction : Action {
    data class Init(val initScreen: ScreenModel) : CommonAction()
}

sealed class CommonSideEffect : Effect

class CommonViewModel(
    val ktor: String = "",
) : Store<CommonState, CommonAction, CommonSideEffect>,
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val stateFlow = MutableStateFlow(initializeState())
    private val sideEffect = MutableSharedFlow<CommonSideEffect>()

    private fun initializeState(): CommonState =
        CommonState()

    override fun observeState(): StateFlow<CommonState> = stateFlow

    override fun observeSideEffect(): Flow<CommonSideEffect> = sideEffect

    override fun dispatch(action: CommonAction) {
        val oldState = stateFlow.value
		val newState = when (action) {
			is CommonAction.Init -> oldState.copy(screenModel = action.initScreen)
		}

		if (newState != oldState) {
			stateFlow.value = newState
		}
	}
}
