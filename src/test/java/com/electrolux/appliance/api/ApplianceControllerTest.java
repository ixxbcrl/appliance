package com.electrolux.appliance.api;

import com.electrolux.appliance.apis.ApplianceController;
import com.electrolux.appliance.apis.dto.response.AppliancePingRspDTO;
import com.electrolux.appliance.application.query.ApplianceQueryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplianceController.class)
@RunWith(SpringRunner.class)
public class ApplianceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ApplianceQueryService applianceQueryService;

    @Test
    void should_have_ok_status() throws Exception {
        when(applianceQueryService.getApplianceStatus(anyList()))
                .thenReturn(Collections.singletonList(AppliancePingRspDTO
                        .builder()
                        .applianceID("123")
                        .appliancePing("CONNECTED")
                        .build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/appliance/status?applianceIDs=123"))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_expeted_response() throws Exception {
        when(applianceQueryService.getApplianceStatus(anyList()))
                .thenReturn(Collections.singletonList(AppliancePingRspDTO
                        .builder()
                        .applianceID("123")
                        .appliancePing("CONNECTED")
                        .build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/appliance/status?applianceIDs=123"))
                .andExpect(jsonPath("$[0].applianceID", is("123")))
                .andExpect(jsonPath("$[0].appliancePing", is("CONNECTED")));
    }
}
