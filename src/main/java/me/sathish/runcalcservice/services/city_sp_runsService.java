package me.sathish.runcalcservice.services;

import java.time.Duration;
import me.sathish.runcalcservice.entities.city_sp_runs;
import me.sathish.runcalcservice.model.response.PagedResult;
import me.sathish.runcalcservice.repositories.city_sp_runsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

@Service
@Transactional
public class city_sp_runsService {

    private final city_sp_runsRepository citySpRunsRepository;

    @Autowired
    public city_sp_runsService(city_sp_runsRepository citySpRunsRepository) {
        this.citySpRunsRepository = citySpRunsRepository;
    }

    public PagedResult<city_sp_runs> findAllcity_sp_runss(
            int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort =
                sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                        ? Sort.by(sortBy).ascending()
                        : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<city_sp_runs> citySpRunssPage = citySpRunsRepository.findAll(pageable);

        return new PagedResult<>(citySpRunssPage);
    }

    public Flux<city_sp_runs> findcity_sp_runsById(Long id) {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Processing for record: " + i))
                .map(i -> new city_sp_runs(Long.valueOf(i), "The returned value " + i));
    }

    public city_sp_runs savecity_sp_runs(city_sp_runs citySpRuns) {
        return citySpRunsRepository.save(citySpRuns);
    }

    public void deletecity_sp_runsById(Long id) {
        citySpRunsRepository.deleteById(id);
    }
}
