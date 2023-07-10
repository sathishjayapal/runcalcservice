package me.sathish.runcalcservice.web.controllers;

import lombok.extern.slf4j.Slf4j;
import me.sathish.runcalcservice.entities.city_sp_runs;
import me.sathish.runcalcservice.model.response.PagedResult;
import me.sathish.runcalcservice.services.city_sp_runsService;
import me.sathish.runcalcservice.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("")
@Slf4j
public class city_sp_runsController {

    private final city_sp_runsService citySpRunsService;

    @Autowired
    public city_sp_runsController(city_sp_runsService citySpRunsService) {
        this.citySpRunsService = citySpRunsService;
    }

    @GetMapping
    public PagedResult<city_sp_runs> getAllcity_sp_runss(
            @RequestParam(
                            value = "pageNo",
                            defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,
                            required = false)
                    int pageNo,
            @RequestParam(
                            value = "pageSize",
                            defaultValue = AppConstants.DEFAULT_PAGE_SIZE,
                            required = false)
                    int pageSize,
            @RequestParam(
                            value = "sortBy",
                            defaultValue = AppConstants.DEFAULT_SORT_BY,
                            required = false)
                    String sortBy,
            @RequestParam(
                            value = "sortDir",
                            defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,
                            required = false)
                    String sortDir) {
        return citySpRunsService.findAllcity_sp_runss(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public Flux<city_sp_runs> getcity_sp_runsById(@PathVariable Long id) {
        return citySpRunsService.findcity_sp_runsById(id);
    }
    @GetMapping(value = "/{id}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<city_sp_runs> getcity_sp_runsByIdStream(@PathVariable Long id) {
        return citySpRunsService.findcity_sp_runsById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public city_sp_runs createcity_sp_runs(@RequestBody @Validated city_sp_runs citySpRuns) {
        return citySpRunsService.savecity_sp_runs(citySpRuns);
    }

    @PutMapping("/{id}")
    public Flux<city_sp_runs> updatecity_sp_runs(
            @PathVariable Long id, @RequestBody city_sp_runs citySpRuns) {
        return citySpRunsService.findcity_sp_runsById(id);
    }

    @DeleteMapping("/{id}")
    public Flux<city_sp_runs> deletecity_sp_runs(@PathVariable Long id) {
        return citySpRunsService.findcity_sp_runsById(id);
    }
}
