package org.example.testing.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.example.testing.dto.GameRequest;
import org.example.testing.dto.GameStatsRequest;
import org.example.testing.model.Game;
import org.example.testing.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Operation(
            summary = "JWT-protected endpoint",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/addGame")
    public String addGame(@RequestBody GameRequest request) {
        return gameService.addGame(request);

    }


    @Operation(
            summary = "Some JWT-protected endpoint",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/stats")
    public List<Game> getGameStats() {
        return gameService.getGameStats();
    }
}