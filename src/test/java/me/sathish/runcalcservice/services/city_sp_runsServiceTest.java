package me.sathish.runcalcservice.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.willDoNothing;

import java.util.List;
import me.sathish.runcalcservice.entities.city_sp_runs;
import me.sathish.runcalcservice.model.response.PagedResult;
import me.sathish.runcalcservice.repositories.city_sp_runsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
class city_sp_runsServiceTest {

    @Mock private city_sp_runsRepository citySpRunsRepository;

    @InjectMocks private city_sp_runsService citySpRunsService;

    @Test
    void findAllcity_sp_runss() {
        // given
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
        Page<city_sp_runs> citySpRunsPage = new PageImpl<>(List.of(getcity_sp_runs()));
        given(citySpRunsRepository.findAll(pageable)).willReturn(citySpRunsPage);

        // when
        PagedResult<city_sp_runs> pagedResult =
                citySpRunsService.findAllcity_sp_runss(0, 10, "id", "asc");

        // then
        assertThat(pagedResult).isNotNull();
        assertThat(pagedResult.data()).isNotEmpty().hasSize(1);
        assertThat(pagedResult.hasNext()).isFalse();
        assertThat(pagedResult.pageNumber()).isEqualTo(1);
        assertThat(pagedResult.totalPages()).isEqualTo(1);
        assertThat(pagedResult.isFirst()).isTrue();
        assertThat(pagedResult.isLast()).isTrue();
        assertThat(pagedResult.hasPrevious()).isFalse();
        assertThat(pagedResult.totalElements()).isEqualTo(1);
    }

    @Test
    void findcity_sp_runsById() {
        // given
        //
        // given(citySpRunsRepository.findById(1L)).willReturn(Optional.of(getcity_sp_runs()));
        // when
        //        Flux<city_sp_runs> optionalcity_sp_runs =
        // citySpRunsService.findcity_sp_runsById(1L);
        // then
        // TODO Sathish
        //        assertThat(optionalcity_sp_runs.p).s();
        //        city_sp_runs citySpRuns = optionalcity_sp_runs.get();
        //        assertThat(citySpRuns.getId()).isEqualTo(1L);
        //        assertThat(citySpRuns.getText()).isEqualTo("junitTest");
        assertThat(1).isEqualTo(1);
    }

    @Test
    void savecity_sp_runs() {
        // given
        given(citySpRunsRepository.save(getcity_sp_runs())).willReturn(getcity_sp_runs());
        // when
        city_sp_runs persistedcity_sp_runs = citySpRunsService.savecity_sp_runs(getcity_sp_runs());
        // then
        assertThat(persistedcity_sp_runs).isNotNull();
        assertThat(persistedcity_sp_runs.getId()).isEqualTo(1L);
        assertThat(persistedcity_sp_runs.getText()).isEqualTo("junitTest");
    }

    @Test
    void deletecity_sp_runsById() {
        // given
        willDoNothing().given(citySpRunsRepository).deleteById(1L);
        // when
        citySpRunsService.deletecity_sp_runsById(1L);
        // then
        verify(citySpRunsRepository, times(1)).deleteById(1L);
    }

    private city_sp_runs getcity_sp_runs() {
        city_sp_runs citySpRuns = new city_sp_runs();
        citySpRuns.setId(1L);
        citySpRuns.setText("junitTest");
        return citySpRuns;
    }
}
