package org.example.testing.unit;

import org.example.testing.dto.DashboardResponse;
import org.example.testing.model.Game;
import org.example.testing.repository.GameRepository;
import org.example.testing.service.DashboardService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardServiceTest {

    @InjectMocks
    private DashboardService dashboardService;

    @Mock
    private GameRepository gameRepository;

    public DashboardServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDashboardData() {

        Game game1 = new Game();
        game1.setPoints(10);
        game1.setRebounds(4);
        game1.setAssists(4);

        Game game2 = new Game();
        game2.setPoints(12);
        game2.setRebounds(6);
        game2.setAssists(6);

        List<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        when(gameRepository.findAll()).thenReturn(games);

        DashboardResponse response = dashboardService.getDashboardData();

        assertEquals(2, response.getTotalGames());
        assertEquals(11.00, response.getAvgPoints());
        assertEquals(5.00, response.getAvgRebounds());
        assertEquals(5.00, response.getAvgAssists());
    }

    @Test
    public void testGetDashboardDataWithNoRecord() {
        List<Game> games = new ArrayList<>();
        when(gameRepository.findAll()).thenReturn(games);
        DashboardResponse response = dashboardService.getDashboardData();
        assertEquals(0, response.getTotalGames());
        assertEquals(0, response.getAvgPoints());
    }
}
