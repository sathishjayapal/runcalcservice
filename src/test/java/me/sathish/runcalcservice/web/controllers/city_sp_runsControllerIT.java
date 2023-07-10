package me.sathish.runcalcservice.web.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import me.sathish.runcalcservice.common.AbstractIntegrationTest;
import me.sathish.runcalcservice.entities.city_sp_runs;
import me.sathish.runcalcservice.repositories.city_sp_runsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

class city_sp_runsControllerIT extends AbstractIntegrationTest {

    @Autowired private city_sp_runsRepository citySpRunsRepository;

    private List<city_sp_runs> citySpRunsList = null;

    @BeforeEach
    void setUp() {
        citySpRunsRepository.deleteAllInBatch();

        citySpRunsList = new ArrayList<>();
        citySpRunsList.add(new city_sp_runs(null, "First city_sp_runs"));
        citySpRunsList.add(new city_sp_runs(null, "Second city_sp_runs"));
        citySpRunsList.add(new city_sp_runs(null, "Third city_sp_runs"));
        citySpRunsList = citySpRunsRepository.saveAll(citySpRunsList);
    }

    @Test
    void shouldFetchAllcity_sp_runss() throws Exception {
        this.mockMvc
                .perform(get(""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()", is(citySpRunsList.size())))
                .andExpect(jsonPath("$.totalElements", is(3)))
                .andExpect(jsonPath("$.pageNumber", is(1)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.isFirst", is(true)))
                .andExpect(jsonPath("$.isLast", is(true)))
                .andExpect(jsonPath("$.hasNext", is(false)))
                .andExpect(jsonPath("$.hasPrevious", is(false)));
    }

    @Test
    void shouldFindcity_sp_runsById() throws Exception {
        city_sp_runs citySpRuns = citySpRunsList.get(0);
        Long citySpRunsId = citySpRuns.getId();

        this.mockMvc
                .perform(get("/{id}", citySpRunsId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(citySpRuns.getId()), Long.class))
                .andExpect(jsonPath("$.text", is(citySpRuns.getText())));
    }

    @Test
    void shouldCreateNewcity_sp_runs() throws Exception {
        city_sp_runs citySpRuns = new city_sp_runs(null, "New city_sp_runs");
        this.mockMvc
                .perform(
                        post("").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(citySpRuns)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.text", is(citySpRuns.getText())));
    }

    @Test
    void shouldReturn400WhenCreateNewcity_sp_runsWithoutText() throws Exception {
        city_sp_runs citySpRuns = new city_sp_runs(null, null);

        this.mockMvc
                .perform(
                        post("").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(citySpRuns)))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", is("application/problem+json")))
                .andExpect(jsonPath("$.type", is("about:blank")))
                .andExpect(jsonPath("$.title", is("Constraint Violation")))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.detail", is("Invalid request content.")))
                .andExpect(jsonPath("$.instance", is("")))
                .andExpect(jsonPath("$.violations", hasSize(1)))
                .andExpect(jsonPath("$.violations[0].field", is("text")))
                .andExpect(jsonPath("$.violations[0].message", is("Text cannot be empty")))
                .andReturn();
    }

    @Test
    void shouldUpdatecity_sp_runs() throws Exception {
        city_sp_runs citySpRuns = citySpRunsList.get(0);
        citySpRuns.setText("Updated city_sp_runs");

        this.mockMvc
                .perform(
                        put("/{id}", citySpRuns.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(citySpRuns)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(citySpRuns.getId()), Long.class))
                .andExpect(jsonPath("$.text", is(citySpRuns.getText())));
    }

    @Test
    void shouldDeletecity_sp_runs() throws Exception {
        city_sp_runs citySpRuns = citySpRunsList.get(0);

        this.mockMvc
                .perform(delete("/{id}", citySpRuns.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(citySpRuns.getId()), Long.class))
                .andExpect(jsonPath("$.text", is(citySpRuns.getText())));
    }
}
