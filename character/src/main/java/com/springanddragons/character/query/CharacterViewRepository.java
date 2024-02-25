package com.springanddragons.character.query;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CharacterViewRepository extends CrudRepository<CharacterView, UUID> {
}
