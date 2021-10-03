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
import ru.digitalleague.core.model.CarModel;
import ru.digitalleague.core.repository.CarRepository;

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
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CarController carController;
    @MockBean
    private CarRepository carRepository;

    @Test
    public void shouldGetOkStatusGettingAllCars() throws Exception {
        when(carRepository.findAll()).thenReturn(Arrays.asList(
                new CarModel(1L,"ferra"),
                new CarModel(2L, "lamba")
        ));
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/car/allCars")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[*].carId",containsInAnyOrder(1,2)))
                .andExpect(jsonPath("$[*].model",containsInAnyOrder("ferra","lamba")));
    }

    @Test
    public void shouldGetOkStatusGettingById() throws Exception {
        when(carRepository.findById(anyLong())).thenReturn(Optional.of(
                new CarModel(1L,"ferra")));
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/car/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.carId",equalTo(1)))
                .andExpect(jsonPath("$.model",equalTo("ferra")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetOkStatusDeletingById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/deleteCar/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetOkStatusCreatingCar() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/addCar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"model\":\"ferra\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetOkStatusUpdatingCar() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/updateCar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\" ,\"model\":\"ferra\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetCarControllerAndReturnNotNull() {
        assertThat(carController).isNotNull();
    }
}
