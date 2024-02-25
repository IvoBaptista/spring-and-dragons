package com.springanddragons.character.api;

import lombok.*;

import java.util.UUID;

@Data
public class CharacterResponse {
    private UUID id;
    private String name;
}
