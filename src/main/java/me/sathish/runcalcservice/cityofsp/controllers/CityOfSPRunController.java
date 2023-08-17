package me.sathish.runcalcservice.cityofsp.controllers;

import lombok.extern.slf4j.Slf4j;
import me.sathish.runcalcservice.cityofsp.entities.CityOfSPRunDTO;
import me.sathish.runcalcservice.cityofsp.services.CityOfSPRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("cityofspruns")
@Slf4j
public class CityOfSPRunController {
    private final CityOfSPRunService cityOfSpRunService;

    @Autowired
    public CityOfSPRunController(CityOfSPRunService cityOfSpRunService) {
        this.cityOfSpRunService = cityOfSpRunService;
    }

    @GetMapping("findAllSPRuns")
    public Flux<CityOfSPRunDTO> findAllSPRuns() {
        return this.cityOfSpRunService.findAllCitySPRuns();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<CityOfSPRunDTO>> getCityOfSPRunByID (@PathVariable String id) {
        return this.cityOfSpRunService
                .findCitySPRunByID(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
    @PostMapping
    public Mono<CityOfSPRunDTO> addCityOfSPRun(
            @RequestBody Mono<CityOfSPRunDTO> cityOfSPRunDTOMono) {
        return this.cityOfSpRunService.insertCitySPRun(cityOfSPRunDTOMono);
    }
    @PutMapping
    public Mono<ResponseEntity<CityOfSPRunDTO>> updateCityOfSpRun(
            @PathVariable String ID, @RequestBody Mono<CityOfSPRunDTO> cityOfSPRunDTOMono) {
        return this.cityOfSpRunService
                .updateCitySPRun(ID, cityOfSPRunDTOMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{ID}")
    public Mono<Void> deleteCityOfSPRun(@PathVariable String ID) {
        return this.cityOfSpRunService.deleteCityOfSPSun(ID);
    }
}
