package com.springanddragons.character.query;

import com.springanddragons.character.event.CharacterCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
@Slf4j
public class CharacterProjector {
    private final CharacterViewRepository repository;
    private final ViewEventMapper viewEventMapper;

    @EventHandler
    public void on(CharacterCreatedEvent event) {
        log.info("Handling CharacterCreatedEvent: " + event.getId());
        CharacterView character = viewEventMapper.toView(event);
        repository.save(character);
    }

    @QueryHandler
    public CharacterView handle(FindCharacterQuery query) {
        return repository.findById(query.getCharacterId())
                .orElseThrow(NoSuchElementException::new);
    }

    @QueryHandler
    public List<CharacterView> handle(FindAllCharactersQuery query) {
        log.info("Handling FindAllCharacters");
        List<CharacterView> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        log.info("Found " + list.size() + " characters.");
        return list;
    }

    @Mapper(componentModel = "spring")
    public interface ViewEventMapper {
        CharacterView toView(CharacterCreatedEvent source);
    }
}
