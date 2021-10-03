package ru.digitalleague.core.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.digitalleague.core.model.CityQueue;
import ru.digitalleague.core.repository.CityQueueRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CityQueueControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CityQueueController cityQueueController;
    @MockBean
    private CityQueueRepository cityQueueRepository;

    @Test
    public void shouldGetOkStatusGettingAllCityQueues() throws Exception {
        when(cityQueueRepository.findAll()).thenReturn(Arrays.asList(
                new CityQueue(1L,"Moscow","Moscow_queue"),
                new CityQueue(2L,"St.P","St.P_queue")
        ));
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/allQueues")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[*].cityId",containsInAnyOrder(1,2)))
                .andExpect(jsonPath("$[*].name",containsInAnyOrder("Moscow","St.P")))
                .andExpect(jsonPath("$[*].queue",containsInAnyOrder("Moscow_queue","St.P_queue")));
    }

    @Test
    public void shouldGetOkStatusGettingById() throws Exception {
        when(cityQueueRepository.findById(anyLong())).thenReturn(Optional.of(
                new CityQueue(44L,"Moscow","Moscow_queue")));
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/queue/44")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cityId",equalTo(44)))
                .andExpect(jsonPath("$.name",equalTo("Moscow")))
                .andExpect(jsonPath("$.queue",equalTo("Moscow_queue")));
    }

    @Test
    public void shouldGetOkStatusDeletingById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/deleteQueue/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetOkStatusCreatingQueue() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/addQueue")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"cityId\":\"1\",\"name\":\"Moscow\",\"queue\":\"Moscow_queue\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetOkStatusUpdatingQueue() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/updateQueue")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"cityId\":\"1\",\"name\":\"Moscow\",\"queue\":\"Moscow_queue\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetCarControllerAndReturnNotNull() {
        assertThat(cityQueueController).isNotNull();
    }
}
