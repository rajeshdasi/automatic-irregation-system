package com.globomantics.plots.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globomantics.plots.models.Plot;
import com.globomantics.plots.models.exceptions.BadRequest;
import com.globomantics.plots.service.PlotsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.Clock;
import java.time.ZonedDateTime;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PlotsControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private PlotsService plotsService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void shouldReturnPlots() throws Exception {
        Plot plot1 = new Plot(1, "Prakash", "Rice", 450, ZonedDateTime.now(Clock.systemUTC()));
        when(plotsService.getAllPlots()).thenReturn(singletonList(plot1));

        this.mockMvc.perform(
                get("/api/v1/plots"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Prakash")));
    }

    @Test
        void shouldThrowExceptionWhenPlotDoesNotExist() throws Exception {

        when(plotsService.getPlot(1))
                .thenThrow(new BadRequest("Plot with number 1 is not present"));

        this.mockMvc.perform(
                        get("/api/v1/plots/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(
                        containsString("Plot with number 1 is not present")));
    }

    @Test
    void shouldSavePlot() throws Exception {
        Plot plot = new Plot();
        plot.setPlotOwner("Jay");
        when(plotsService.createPlot(any(), any())).thenReturn(plot);

        this.mockMvc.perform(
                        post("/api/v1/plots/4").content(asJsonString(plot))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Jay")));
    }

    @Test
    void shouldUpdatePlot() throws Exception {
        Plot plot = new Plot();
        plot.setPlotOwner("Jay");
        when(plotsService.createPlot(any(), any())).thenReturn(plot);

        this.mockMvc.perform(
                        put("/api/v1/plots/4").content(asJsonString(plot))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeletePlot() throws Exception {
        Plot plot = new Plot();
        plot.setPlotOwner("Jay");
        when(plotsService.createPlot(any(), any())).thenReturn(plot);

        this.mockMvc.perform(
                        delete("/api/v1/plots/4"))
                                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}