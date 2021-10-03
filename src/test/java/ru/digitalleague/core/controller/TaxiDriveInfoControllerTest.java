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
import ru.digitalleague.core.model.TaxiDriveInfo;
import ru.digitalleague.core.repository.TaxiDriveInfoRepository;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TaxiDriveInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TaxiDriveInfoController taxiDriveInfoController;
    @MockBean
    private TaxiDriveInfoRepository taxiDriveInfoRepository;

    @Test
    public void shouldGetOkStatusGettingAllTaxiDriverInfo() throws Exception {
        when(taxiDriveInfoRepository.findAll()).thenReturn(Arrays.asList(
                new TaxiDriveInfo("Jorik","Vartanov","Kerbek",1,"camry"),
                new TaxiDriveInfo("Ivan","Ivanov","Ivanovich",1,"k5")
        ));
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/taxiDriveInfo")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[*].lastName",containsInAnyOrder("Jorik","Ivan")))
                .andExpect(jsonPath("$[*].firstName",containsInAnyOrder("Vartanov","Ivanov")))
                .andExpect(jsonPath("$[*].middleName",containsInAnyOrder("Kerbek","Ivanovich")))
                .andExpect(jsonPath("$[*].level",containsInAnyOrder(1,1)))
                .andExpect(jsonPath("$[*].carModel",containsInAnyOrder("camry","k5")));
    }

    @Test
    public void shouldGetOkStatusDeletingById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/deleteTaxiDriveInfo/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetOkStatusCreatingTaxiDriveInfo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/addTaxiDriveInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"lastName\":\"Jorik\",\"firstName\":\"Jorik\",\"middleName\":\"Jorik\",\"level\":1,\"carModel\":\"camry\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetOkStatusUpdatingTaxiDriveInfo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/updateTaxiOrderInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"lastName\":\"Jorik\",\"firstName\":\"Jorik\",\"middleName\":\"Jorik\",\"level\":1,\"carModel\":\"camry\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetCarControllerAndReturnNotNull() {
        assertThat(taxiDriveInfoController).isNotNull();
    }

}
