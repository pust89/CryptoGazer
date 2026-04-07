package com.pustovit.cryptogazer.tea.reducer

import com.pustovit.cryptogazer.tea.Update
import com.pustovit.cryptogazer.tea.command.Command as C
import com.pustovit.cryptogazer.tea.side_effect.SideEffect as S
import com.pustovit.cryptogazer.tea.event.Event as E

interface Reducer<Event : E, State, SideEffect : S, Command : C> {
    fun reduce(event: Event): Update<State, SideEffect, Command>
}