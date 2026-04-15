package com.pustovit.cryptogazer.onboarding.tea

import com.pustovit.cryptogazer.domain.model.resource.asResource
import com.pustovit.cryptogazer.domain.repository.OnboardingRepository
import com.pustovit.cryptogazer.tea.CommandHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import me.tatarka.inject.annotations.Inject

@Inject
class OnboardingCommandHandler(val onboardingRepository: OnboardingRepository) : CommandHandler<
        OnboardingCommand,
        OnboardingEvent> {

    private val commands: MutableSharedFlow<OnboardingCommand> = MutableSharedFlow()

    override fun getEvents(): Flow<OnboardingEvent> {
        return commands.flatMapMerge { command ->
            when (command) {
                OnboardingCommand.LoadCards -> loadCards()
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