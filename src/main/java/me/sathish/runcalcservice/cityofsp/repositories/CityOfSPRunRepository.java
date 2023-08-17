package me.sathish.runcalcservice.cityofsp.repositories;

import me.sathish.runcalcservice.cityofsp.entities.CityOfSPRun;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityOfSPRunRepository extends ReactiveMongoRepository<CityOfSPRun, String> {}
