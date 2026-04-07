package com.pustovit.cryptogazer.tea

import com.pustovit.cryptogazer.tea.side_effect.SideEffect as S
import com.pustovit.cryptogazer.tea.command.Command as C


interface Event
interface Command

class Update<State, SideEffect : S, Command : C> internal constructor(
    val state: State? = null,
    val sideEffects: List<SideEffect>? = null,
    val commands: List<Command>? = null
) {

    companion object {

        fun <State, SideEffect : S, Command : C> from(
            newState: State? = null,
            sideEffects: List<SideEffect>? = null,
            commands: List<Command>? = null
        ): Update<State, SideEffect, Command> {
            return Update(
                state = newState,
                sideEffects = sideEffects,
                commands = commands,
            )
        }
    }
}

