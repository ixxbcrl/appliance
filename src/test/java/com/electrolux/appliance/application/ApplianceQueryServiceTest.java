package com.electrolux.appliance.application;

import com.electrolux.appliance.apis.dto.response.AppliancePingRspDTO;
import com.electrolux.appliance.application.exceptions.WebServiceException;
import com.electrolux.appliance.application.query.ApplianceQueryService;
import com.electrolux.appliance.repository.ApplianceRepository;
import com.electrolux.appliance.repository.entity.Appliance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplianceQueryServiceTest {

    @Mock
    ApplianceRepository applianceRepository;

    @InjectMocks
    ApplianceQueryService applianceQueryService;

    @Test
    void should_return_appliance_status() {
        List<Appliance> appliances = Collections.singletonList(Appliance
                .builder()
                .applianceId("123")
                .factoryNo("factory123")
                .build());

        when(applianceRepository.findApplianceByApplianceIdIn(anyList())).thenReturn(appliances);

        List<AppliancePingRspDTO> result = applianceQueryService.getApplianceStatus(Collections.singletonList("123"));

        assertEquals(1, result.size());
        assertEquals("123", result.get(0).getApplianceID());
    }

    @Test
    void should_throw_appliance_not_found_exception() {
        when(applianceRepository.findApplianceByApplianceIdIn(anyList())).thenReturn(null);

        WebServiceException ex = assertThrows(WebServiceException.class,
                () -> applianceQueryService.getApplianceStatus(Collections.singletonList("123")));

        assertEquals("The appliances was not found: [123]", ex.getMessage());
    }
}
