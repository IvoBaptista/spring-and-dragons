package com.springanddragons.character.query;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Data
public class CharacterView {
    @Id
    private UUID id;
    private String name;

}
