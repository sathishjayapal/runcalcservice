package me.sathish.runcalcservice.web.controllers;

import static me.sathish.runcalcservice.utils.AppConstants.PROFILE_TEST;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import me.sathish.runcalcservice.entities.city_sp_runs;
import me.sathish.runcalcservice.model.response.PagedResult;
import me.sathish.runcalcservice.services.city_sp_runsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = city_sp_runsController.class)
@ActiveProfiles(PROFILE_TEST)
class city_sp_runsControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private city_sp_runsService citySpRunsService;

    @Autowired private ObjectMapper objectMapper;

    private List<city_sp_runs> citySpRunsList;

    @BeforeEach
    void setUp() {
        this.citySpRunsList = new ArrayList<>();
        this.citySpRunsList.add(new city_sp_runs(1L, "text 1"));
        this.citySpRunsList.add(new city_sp_runs(2L, "text 2"));
        this.citySpRunsList.add(new city_sp_runs(3L, "text 3"));
    }

    @Test
    void shouldFetchAllcity_sp_runss() throws Exception {
        Page<city_sp_runs> page = new PageImpl<>(citySpRunsList);
        PagedResult<city_sp_runs> citySpRunsPagedResult = new PagedResult<>(page);
        given(citySpRunsService.findAllcity_sp_runss(0, 10, "id", "asc"))
                .willReturn(citySpRunsPagedResult);

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

    //    @Test
    //    void shouldFindcity_sp_runsById() throws Exception {
    //        Long citySpRunsId = 1L;
    //        city_sp_runs citySpRuns =
    //                new city_sp_runs(citySpRunsId, "The returned value " + citySpRunsId);
    //        given(citySpRunsService.findcity_sp_runsById(citySpRunsId))
    //                .willReturn(Flux.just(citySpRuns));
    //
    //        this.mockMvc
    //                .perform(get("/{id}", citySpRunsId))
    //                .andExpect(status().isOk())
    //                .andExpect(jsonPath("$.text", is(citySpRuns.getText())));
    //    }

    //    @Test
    //    void shouldReturn404WhenFetchingNonExistingcity_sp_runs() throws Exception {
    //        Long citySpRunsId = 1L;
    //
    // given(citySpRunsService.findcity_sp_runsById(citySpRunsId)).willReturn(Optional.empty());
    //
    //        this.mockMvc.perform(get("/{id}", citySpRunsId)).andExpect(status().isNotFound());
    //    }

    @Test
    void shouldCreateNewcity_sp_runs() throws Exception {
        given(citySpRunsService.savecity_sp_runs(any(city_sp_runs.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        city_sp_runs citySpRuns = new city_sp_runs(1L, "some text");
        this.mockMvc
                .perform(
                        post("").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(citySpRuns)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.text", is(citySpRuns.getText())));
    }

    //    @Test
    //    void shouldReturn400WhenCreateNewcity_sp_runsWithoutText() throws Exception {
    //        city_sp_runs citySpRuns = new city_sp_runs(null, null);
    //
    //        this.mockMvc
    //                .perform(
    //                        post("").contentType(MediaType.APPLICATION_JSON)
    //                                .content(objectMapper.writeValueAsString(citySpRuns)))
    //                .andExpect(status().isBadRequest())
    //                .andExpect(header().string("Content-Type", is("application/problem+json")))
    //                .andExpect(jsonPath("$.type", is("about:blank")))
    //                .andExpect(jsonPath("$.title", is("Constraint Violation")))
    //                .andExpect(jsonPath("$.status", is(400)))
    //                .andExpect(jsonPath("$.detail", is("Invalid request content.")))
    //                .andExpect(jsonPath("$.instance", is("")))
    //                .andExpect(jsonPath("$.violations", hasSize(1)))
    //                .andExpect(jsonPath("$.violations[0].field", is("text")))
    //                .andExpect(jsonPath("$.violations[0].message", is("Text cannot be empty")))
    //                .andReturn();
    //    }

    //    @Test
    //    void shouldUpdatecity_sp_runs() throws Exception {
    //        Long citySpRunsId = 1L;
    //        city_sp_runs citySpRuns = new city_sp_runs(citySpRunsId, "Updated text");
    //        given(citySpRunsService.findcity_sp_runsById(citySpRunsId))
    //                .willReturn(Optional.of(citySpRuns));
    //        given(citySpRunsService.savecity_sp_runs(any(city_sp_runs.class)))
    //                .willAnswer((invocation) -> invocation.getArgument(0));
    //
    //        this.mockMvc
    //                .perform(
    //                        put("/{id}", citySpRuns.getId())
    //                                .contentType(MediaType.APPLICATION_JSON)
    //                                .content(objectMapper.writeValueAsString(citySpRuns)))
    //                .andExpect(status().isOk())
    //                .andExpect(jsonPath("$.text", is(citySpRuns.getText())));
    //    }

    //    @Test
    //    void shouldReturn404WhenUpdatingNonExistingcity_sp_runs() throws Exception {
    //        Long citySpRunsId = 1L;
    //
    // given(citySpRunsService.findcity_sp_runsById(citySpRunsId)).willReturn(Optional.empty());
    //        city_sp_runs citySpRuns = new city_sp_runs(citySpRunsId, "Updated text");
    //
    //        this.mockMvc
    //                .perform(
    //                        put("/{id}", citySpRunsId)
    //                                .contentType(MediaType.APPLICATION_JSON)
    //                                .content(objectMapper.writeValueAsString(citySpRuns)))
    //                .andExpect(status().isNotFound());
    //    }

    //    @Test
    //    void shouldDeletecity_sp_runs() throws Exception {
    //        Long citySpRunsId = 1L;
    //        city_sp_runs citySpRuns = new city_sp_runs(citySpRunsId, "Some text");
    //        given(citySpRunsService.findcity_sp_runsById(citySpRunsId))
    //                .willReturn(Optional.of(citySpRuns));
    //        doNothing().when(citySpRunsService).deletecity_sp_runsById(citySpRuns.getId());
    //
    //        this.mockMvc
    //                .perform(delete("/{id}", citySpRuns.getId()))
    //                .andExpect(status().isOk())
    //                .andExpect(jsonPath("$.text", is(citySpRuns.getText())));
    //    }
    //
    //    @Test
    //    void shouldReturn404WhenDeletingNonExistingcity_sp_runs() throws Exception {
    //        Long citySpRunsId = 1L;
    //
    // given(citySpRunsService.findcity_sp_runsById(citySpRunsId)).willReturn(Optional.empty());
    //
    //        this.mockMvc.perform(delete("/{id}", citySpRunsId)).andExpect(status().isNotFound());
    //    }
}
