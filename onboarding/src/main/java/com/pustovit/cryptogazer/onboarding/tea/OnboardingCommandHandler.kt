package com.pustovit.cryptogazer.onboarding.tea

import android.util.Log
import com.pustovit.cryptogazer.domain.model.resource.asResource
import com.pustovit.cryptogazer.domain.repository.OnboardingRepository
import com.pustovit.cryptogazer.tea.Command
import com.pustovit.cryptogazer.tea.CommandHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


class OnboardingCommandHandler(val onboardingRepository: OnboardingRepository) : CommandHandler<
        OnboardingCommand,
        OnboardingEvent> {


    private val commands: Channel<Command> = Channel(capacity = 16)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getEvents(): Flow<OnboardingEvent> {
        Log.d("actTag", "getEvents: called")
        return commands.consumeAsFlow()
            .flatMapMerge { command ->
                Log.d("actTag", "flatMapMerge 1")
               return@flatMapMerge when (command) {
                    is OnboardingCommand.LoadCards -> loadCards()
                   else -> emptyFlow()
               }
            }
    }

    override suspend fun execute(command: OnboardingCommand) {
        //commands.emit(command)
        commands.send(command)
        Log.d("actTag", "execute: complete")
    }

    private fun loadCards(): Flow<OnboardingEvent> {
        return onboardingRepository.getOnboardingCard()
            .flowOn(Dispatchers.IO)
            .asResource()
            .map { OnboardingEvent.DomainEvent.CardsLoaded(it) }
    }
}