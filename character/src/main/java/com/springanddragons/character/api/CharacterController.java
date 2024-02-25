package com.springanddragons.character.api;

import com.springanddragons.character.command.CreateCharacterCommand;
import com.springanddragons.character.query.CharacterView;
import com.springanddragons.character.query.FindAllCharactersQuery;
import com.springanddragons.character.query.FindCharacterQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/character")
public class CharacterController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final RequestCommandMapper requestCommandMapper;
    private final ViewResponseMapper viewResponseMapper;

    @PostMapping("/create")
    public void createCharacter(@RequestBody CharacterCreationRequest request) {
        commandGateway.send(requestCommandMapper.toCommand(request));
    }

    @GetMapping("/list")
    public CompletableFuture<List<CharacterResponse>> getAllCharacter() {
        return queryGateway.query(new FindAllCharactersQuery(), ResponseTypes.multipleInstancesOf(CharacterView.class))
                .thenApply(viewList -> viewList.stream().map(viewResponseMapper::toResponse)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public CompletableFuture<CharacterResponse> findCharacter(@PathVariable("id") String characterId) {
        return queryGateway.query(new FindCharacterQuery(UUID.fromString(characterId)),
                        ResponseTypes.instanceOf(CharacterView.class))
                .thenApply(viewResponseMapper::toResponse);
    }

    @Mapper(componentModel = "spring")
    public interface RequestCommandMapper {
        CreateCharacterCommand toCommand(CharacterCreationRequest source);
    }

    @Mapper(componentModel = "spring")
    public interface ViewResponseMapper {
        CharacterResponse toResponse(CharacterView source);
    }
}
