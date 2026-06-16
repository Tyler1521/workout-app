package org.example.testing.unit;

import org.example.testing.dto.GameRequest;
import org.example.testing.model.Game;
import org.example.testing.repository.GameRepository;
import org.example.testing.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private org.example.testing.config.LRUCache<String, List<Game>> gameCache;

    @Mock
    private org.example.testing.config.LRUCache<String, Game> singleGameCache;

    @Test
    public void testAddGame() {

        GameRequest request = new GameRequest();
        request.setDate("2024-01-01");
        request.setPoints(10);
        request.setRebounds(4);
        request.setAssists(4);

        String response = gameService.addGame(request);
        assertTrue(response.contains("Game added:"));

    }

    @Test
    public void testAddGameWithError() {

        GameRequest request = new GameRequest();
        request.setDate("2024-01-01");
        request.setPoints(10);
        request.setRebounds(4);
        request.setAssists(4);

        when(gameRepository.save(any())).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> gameService.addGame(request));

    }

    @Test
    public void testGetGameById() {
        Game game1 = new Game();
        game1.setId("123");
        game1.setPoints(10);
        game1.setRebounds(4);
        game1.setAssists(4);

        when(singleGameCache.get(any())).thenReturn(null);
        // Mock repository to return Optional.of(game1)
        when(gameRepository.findById(any())).thenReturn(Optional.of(game1));

        Game response = gameService.getGameById("123");
        assertEquals(game1, response);
    }

    @Test
    public void testGetGameStats() {
        Game game1 = new Game();
        game1.setId("123");
        game1.setPoints(10);
        game1.setRebounds(4);
        game1.setAssists(4);

        when(gameCache.get(any())).thenReturn(null);

        when(gameRepository.findAll()).thenReturn(Collections.singletonList(game1));

        List<Game> games = gameService.getGameStats();

        assertEquals(1, games.size());
    }

    @Test
    public void testGetGameStatsWithCache() {
        Game game1 = new Game();
        game1.setId("123");
        game1.setPoints(10);
        game1.setRebounds(4);
        game1.setAssists(4);

        when(gameCache.get(any())).thenReturn(Collections.singletonList(game1));

        List<Game> games = gameService.getGameStats();

        assertEquals(1, games.size());
    }

}
