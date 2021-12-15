package com.electrolux.appliance.application.query;

import com.electrolux.appliance.apis.dto.response.AppliancePingRspDTO;
import com.electrolux.appliance.application.exceptions.WebServiceException;
import com.electrolux.appliance.enums.ConnectionStatus;
import com.electrolux.appliance.repository.ApplianceRepository;
import com.electrolux.appliance.repository.entity.Appliance;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplianceQueryService {

    private final ApplianceRepository applianceRepository;

    public ApplianceQueryService(ApplianceRepository applianceRepository) {
        this.applianceRepository = applianceRepository;
    }

    public List<AppliancePingRspDTO> getApplianceStatus(List<String> applianceIDs) {
        List<Appliance> appliances = applianceRepository.findApplianceByApplianceIdIn(applianceIDs);

        if (CollectionUtils.isEmpty(appliances)) {
            throw new WebServiceException("The appliances was not found: " + applianceIDs);
        }
        return pingAppliances(appliances);
    }

    //This would be a ping to the actual appliance in the real world
    private List<AppliancePingRspDTO> pingAppliances(List<Appliance> appliances) {
        return appliances.stream()
                .map(
                        it -> AppliancePingRspDTO.builder()
                                .applianceID(it.getApplianceId())
                                .appliancePing(getRandomConnectionStatus(Math.random()).getName())
                                .build()
                ).collect(Collectors.toList());
    }

    //Added unknown in case the appliance is not reachable.
    private ConnectionStatus getRandomConnectionStatus(double ping) {
        if (ping >= 0.6) {
            return ConnectionStatus.CONNECTED;
        }

        if (ping > 0.3) {
            return ConnectionStatus.DISCONNECTED;
        }

        return ConnectionStatus.UNKNOWN;
    }
}
