package com.springanddragons.character.event;

import lombok.*;

import java.util.UUID;

@Data
public class CharacterCreatedEvent {
    private UUID id;
    private String name;

}
