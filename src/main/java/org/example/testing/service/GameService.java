package org.example.testing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.testing.config.LRUCache;
import org.example.testing.dto.GameRequest;
import org.example.testing.dto.GameStatsRequest;
import org.example.testing.model.Game;
import org.example.testing.repository.GameRepository;
import org.example.testing.service.RedisMessagePublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {

    @Autowired
    public GameRepository gameRepository;

    @Autowired
    private LRUCache<String, List<Game>> gameCache;

    @Autowired
    private LRUCache<String, Game> singleGameCache;

    @Autowired
    private RedisMessagePublisher redisMessagePublisher;

    @Autowired
    private ObjectMapper objectMapper; // For serializing GameRequest

    private static final String CACHE_KEY = "allGames";

    public String addGame(GameRequest request) {
        log.info("Publishing new game to queue: {}", request);
        try {
            String message = objectMapper.writeValueAsString(request);
            redisMessagePublisher.publish(message);
            return "Game request queued";
        } catch (Exception e) {
            log.error("Failed to publish game request: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to queue game request: " + e.getMessage());
        }
    }

    public Game getGameById(String id) {
        log.info("Getting game by id: {}", id);

        Game game;

        Game cachedSinglegame = singleGameCache.get(id);
        if (cachedSinglegame != null) {
            log.info("single game found from cache, cache size: {}", cachedSinglegame.getId());
            return cachedSinglegame;
        }

//        log.info("Game not found from cache, calling DB now");
        try {
            game = gameRepository.findById(id).orElse(null);
            if (game != null) {
                singleGameCache.put(id, game);
            }
            return game;
        } catch (Exception e) {
            log.error("Failed to fetch game by id: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch game by id: " + e.getMessage());
        }
    }

    public List<Game> getGameStats() {
        log.info("get all game stats");

        List<Game> cachedGames = gameCache.get(CACHE_KEY);
        if (cachedGames != null) {
            log.info("returning cached games");
            return cachedGames;
        }

        log.info("returning new game stats");
        try {
            List<Game> games = gameRepository.findAll();
            gameCache.put(CACHE_KEY, games);
            return games;
        } catch (Exception e) {
            log.error("Failed to fetch game stats: {}", e.getMessage(), e);
            throw new RuntimeException("Could not retrieve game stats", e);
        }
    }
}