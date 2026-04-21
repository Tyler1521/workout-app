package org.example.testing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.testing.dto.GameRequest;
import org.example.testing.dto.GameStatsRequest;
import org.example.testing.model.Game;
import org.example.testing.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {

    @Autowired
    public GameRepository gameRepository;

    public String addGame(GameRequest request) {
        log.info("Adding new game: {}", request);

        Game game = new Game();
        try {
            game.setDate(request.getDate());
            game.setPoints(request.getPoints());
            game.setRebounds(request.getRebounds());
            game.setAssists(request.getAssists());
            gameRepository.save(game);
        } catch (Exception e) {
            log.error("Failed to fetch game stats: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to add game: " + e.getMessage());
        }

        log.info("game added");
        return "Game added: " + game.getId();
    }

    public List<Game> getGameStats() {
        log.info("get all game stats");
        try {
            return gameRepository.findAll();
        } catch (Exception e) {
            log.error("Failed to fetch game stats: {}", e.getMessage(), e);
            throw new RuntimeException("Could not retrieve game stats", e);
        }
    }
}