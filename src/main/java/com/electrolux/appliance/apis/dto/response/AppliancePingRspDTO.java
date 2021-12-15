package com.electrolux.appliance.apis.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppliancePingRspDTO {
    private String applianceID;
    private String appliancePing;
}
