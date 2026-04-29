package org.example.testing.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.testing.dto.DashboardResponse;
import org.example.testing.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Operation(
    summary = "Some JWT-protected endpoint",
    security = @SecurityRequirement(name = "bearerAuth")
)
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public DashboardResponse getDashboard() {
        return dashboardService.getDashboardData();
    }
}