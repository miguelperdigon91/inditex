package com.farmatodo.test.repository;

import com.farmatodo.test.model.Character;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CharacterRepository extends MongoRepository<Character, String> {

    Optional<Character> findByCharacterId(int id);
}
