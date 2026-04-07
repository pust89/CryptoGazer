package com.pustovit.cryptogazer.tea

import com.pustovit.cryptogazer.tea.Event as E
import com.pustovit.cryptogazer.tea.SideEffect as S
import com.pustovit.cryptogazer.tea.Command as C

interface Reducer<Event : E, State, SideEffect : S, Command : C> {
    fun reduce(event: Event): Update<State, SideEffect, Command>
}