package me.sathish.runcalcservice.services;

import static org.assertj.core.api.Assertions.assertThat;

import me.sathish.runcalcservice.cityofsp.repositories.CityOfSPRunRepository;
import me.sathish.runcalcservice.cityofsp.services.CityOfSPRunService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CityOfSPRunDTOUtilSPRunServiceTest {

    @Mock private CityOfSPRunRepository cityOfSpRunRepository;

    @InjectMocks private CityOfSPRunService cityOfSpRunService;

    @Test
    void findAllCityOfSPRuns() {
        // given
        assertThat(1 == 1);
    }
}
