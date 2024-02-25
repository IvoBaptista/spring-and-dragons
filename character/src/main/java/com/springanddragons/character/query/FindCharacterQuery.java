package com.springanddragons.character.query;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FindCharacterQuery {
    private UUID characterId;
}
