package com.pustovit.cryptogazer.tea

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import com.pustovit.cryptogazer.tea.Event as E
import com.pustovit.cryptogazer.tea.SideEffect as S
import com.pustovit.cryptogazer.tea.Command as C

class Store<State, Event : E, SideEffect : S, Command : C>(
    val reducer: Reducer<State, Event, SideEffect, Command>,
    val commandHandler: CommandHandler<Command, Event>,
    val scope: CoroutineScope,
    initialState: State,
    val initialCommands: List<Command> = emptyList(),
    val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }
) {
    private val coroutineContext: CoroutineContext = SupervisorJob() +
            exceptionHandler +
            Dispatchers.IO

    private val uiEvents = MutableSharedFlow<Event>()

    private val _state = MutableStateFlow<State>(initialState)
    val state = _state.asStateFlow()

    private val _sideEffects = MutableSharedFlow<SideEffect>()
    val sideEffects = _sideEffects.asSharedFlow()

    suspend fun handleEvent(event: Event) {
        uiEvents.emit(event)
    }

    init {
        scope.launch(context = coroutineContext) {
            merge(
                commandHandler.getEvents(),
                uiEvents
            ).onStart {
                if (initialCommands.isNotEmpty()) {
                    initialCommands.forEach { command ->
                        commandHandler.execute(command)
                    }
                }
            }.map { event ->
                reducer.reduce(event)
            }.onEach {

            }.launchIn(this)
        }
    }
}