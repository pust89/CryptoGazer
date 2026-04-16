package com.pustovit.cryptogazer.tea

import kotlinx.coroutines.flow.Flow
import com.pustovit.cryptogazer.tea.Event as E
import com.pustovit.cryptogazer.tea.Command as C

interface CommandHandler<Command : C, Event : E> {

     fun getEvents(): Flow<Event>

     suspend fun execute(command: Command)

}