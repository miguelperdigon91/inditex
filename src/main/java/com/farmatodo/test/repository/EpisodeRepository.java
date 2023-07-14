package com.farmatodo.test.repository;

import com.farmatodo.test.model.Episode;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EpisodeRepository extends MongoRepository<Episode, String> {

    Optional<Episode> findByNumber(int number);
}
