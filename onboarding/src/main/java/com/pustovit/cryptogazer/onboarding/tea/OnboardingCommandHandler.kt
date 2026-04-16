package com.pustovit.cryptogazer.onboarding.tea

import android.util.Log
import com.pustovit.cryptogazer.domain.model.resource.Resource
import com.pustovit.cryptogazer.domain.model.resource.asResource
import com.pustovit.cryptogazer.domain.repository.OnboardingRepository
import com.pustovit.cryptogazer.tea.CommandHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

class OnboardingCommandHandler(val onboardingRepository: OnboardingRepository) : CommandHandler<
        OnboardingCommand,
        OnboardingEvent> {

    private val commands: MutableSharedFlow<OnboardingCommand> = MutableSharedFlow()


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getEvents(): Flow<OnboardingEvent> {
        Log.d("actTag", "getEvents: called")
        return commands.flatMapMerge { command ->
            Log.d("actTag", "flatMapMerge: $command")

            when (command) {
                is OnboardingCommand.LoadCards -> loadCards()
            }
        }
    }

    override suspend fun execute(command: OnboardingCommand) {
        commands.emit(command)
    }

    private fun loadCards(): Flow<OnboardingEvent> {
        return onboardingRepository.getOnboardingCard()
            .asResource()
            .map { OnboardingEvent.DomainEvent.CardsLoaded(it) }
    }
}