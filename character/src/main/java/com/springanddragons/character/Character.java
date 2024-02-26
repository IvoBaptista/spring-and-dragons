package com.springanddragons.character;

import com.springanddragons.character.command.CreateCharacterCommand;
import com.springanddragons.character.event.CharacterCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
@Slf4j
public class Character {
    @AggregateIdentifier
    private UUID id;
    private String name;

    @CommandHandler
    public Character(CreateCharacterCommand command, final CommandEventMapper commandEventMapper) {
        UUID uuid = UUID.randomUUID();
        log.info("Creating character: " + uuid);
        AggregateLifecycle.apply(commandEventMapper.toEvent(command, uuid));
    }

    @EventSourcingHandler
    public void on(CharacterCreatedEvent event) {
        log.info("Sourcing CharacterCreatedEvent: " + event.getId());
        this.id = event.getId();
        this.name = event.getName();
    }

    @Mapper(componentModel = "spring")
    public interface CommandEventMapper {
        @Mapping(target = "id", source = "id")
        CharacterCreatedEvent toEvent(CreateCharacterCommand source, UUID id);
    }
}
