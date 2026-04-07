package com.pustovit.cryptogazer.tea

import com.pustovit.cryptogazer.tea.SideEffect as S
import com.pustovit.cryptogazer.tea.Command as C

class Update<State, SideEffect : S, Command : C> internal constructor(
    internal val state: State? = null,
    internal val sideEffects: List<SideEffect>? = null,
    internal val commands: List<Command>? = null
) {

    companion object {

        fun <State, SideEffect : S, Command : C> nothing(): Update<State, SideEffect, Command> {
            return Update()
        }

        fun <State, SideEffect : S, Command : C> from(
            state: State? = null,
            sideEffects: List<SideEffect>? = null,
            commands: List<Command>? = null
        ): Update<State, SideEffect, Command> {
            return Update(
                state = state,
                sideEffects = sideEffects,
                commands = commands,
            )
        }
    }
}

