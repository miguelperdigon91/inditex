package com.farmatodo.test.repository;

import com.farmatodo.test.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LocationRepository extends MongoRepository<Location, String> {

    Optional<Location> findByLocationId(int id);
}
