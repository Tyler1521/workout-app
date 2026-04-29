package org.example.testing.controller;

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
@Operation(
    summary = "Some JWT-protected endpoint",
    security = @SecurityRequirement(name = "bearerAuth")
)
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/addGame")
    public String addGame(@RequestBody GameRequest request) {
        return gameService.addGame(request);

    }


    @GetMapping("/stats")
    public List<Game> getGameStats() {
        return gameService.getGameStats();
    }
}