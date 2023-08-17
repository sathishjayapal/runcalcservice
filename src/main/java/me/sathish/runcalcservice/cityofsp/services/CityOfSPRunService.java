package me.sathish.runcalcservice.cityofsp.services;

import me.sathish.runcalcservice.cityofsp.entities.CityOfSPRunDTO;
import me.sathish.runcalcservice.cityofsp.entities.CityOfSPRunDTOUtil;
import me.sathish.runcalcservice.cityofsp.repositories.CityOfSPRunRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CityOfSPRunService {

    private final CityOfSPRunRepository cityOfSpRunRepository;

    public CityOfSPRunService(CityOfSPRunRepository cityOfSpRunRepository) {
        this.cityOfSpRunRepository = cityOfSpRunRepository;
    }

    public Flux<CityOfSPRunDTO> findAllCitySPRuns() {
        return this.cityOfSpRunRepository.findAll().map(CityOfSPRunDTOUtil::toCitySPRunDTO);
    }

    public Mono<CityOfSPRunDTO> findCitySPRunByID(String id) {
        return this.cityOfSpRunRepository.findById(id).map(CityOfSPRunDTOUtil::toCitySPRunDTO);
    }

    public Mono<CityOfSPRunDTO> insertCitySPRun(
            @RequestBody Mono<CityOfSPRunDTO> cityOfSPRunDTOMono) {
        return cityOfSPRunDTOMono
                .map(CityOfSPRunDTOUtil::toEntityCitySPRun)
                .flatMap(this.cityOfSpRunRepository::insert)
                .map(CityOfSPRunDTOUtil::toCitySPRunDTO);
    }

    public Mono<CityOfSPRunDTO> updateCitySPRun(
            String id, Mono<CityOfSPRunDTO> cityOfSPRunDTOMono) {
        // TODO find someway of getting one data entity
        return this.cityOfSpRunRepository
                .findById(id)
                .flatMap(
                        foundData ->
                                cityOfSPRunDTOMono
                                        .map(CityOfSPRunDTOUtil::toEntityCitySPRun)
                                        .doOnNext(e -> e.setId(id)))
                .flatMap(this.cityOfSpRunRepository::save)
                .map(CityOfSPRunDTOUtil::toCitySPRunDTO);
    }

    public Mono<Void> deleteCityOfSPSun(String ID) {
        return this.cityOfSpRunRepository.deleteById(ID);
    }
}
