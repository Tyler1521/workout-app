package org.example.testing.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.testing.config.LRUCache;
import org.example.testing.dto.GameRequest;
import org.example.testing.model.Game;
import org.example.testing.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class RedisMessageSubscriber implements MessageListener {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LRUCache<String, List<Game>> gameCache;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);
            GameRequest request = objectMapper.readValue(msg, GameRequest.class);

            Game game = new Game();
            game.setDate(request.getDate());
            game.setPoints(request.getPoints());
            game.setRebounds(request.getRebounds());
            game.setAssists(request.getAssists());
            gameRepository.save(game);

            gameCache.removeAll("allGames");
            if (cacheManager.getCache("dashboard") != null) {
                cacheManager.getCache("dashboard").clear();
            }

            System.out.println("Game saved from queue: " + game.getId());
        } catch (Exception e) {
            System.err.println("Failed to process game request: " + e.getMessage());
        }
    }
}