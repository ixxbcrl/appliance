package com.electrolux.appliance.apis;

import com.electrolux.appliance.apis.dto.response.AppliancePingRspDTO;
import com.electrolux.appliance.application.query.ApplianceQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/appliance")
public class ApplianceController {

    private final ApplianceQueryService applianceQueryService;

    public ApplianceController(ApplianceQueryService applianceQueryService) {
        this.applianceQueryService = applianceQueryService;
    }

    @GetMapping(value = "/status")
    public ResponseEntity<List<AppliancePingRspDTO>> getApplianceInfo(
            @RequestParam(value = "applianceIDs") List<String> applianceIDs
    ) {
        return ResponseEntity.ok(applianceQueryService.getApplianceStatus(applianceIDs));
    }
}
