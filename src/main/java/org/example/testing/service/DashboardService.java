package org.example.testing.service;

import lombok.extern.slf4j.Slf4j;
import org.example.testing.dto.DashboardException;
import org.example.testing.dto.DashboardResponse;
import org.example.testing.model.Game;
import org.example.testing.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Slf4j
@Service
public class DashboardService {

    @Autowired
    private GameRepository gameRepository;

    public DashboardResponse getDashboardData() {
        log.info("get dashboard data");

        try {
            List<Game> games = gameRepository.findAll();
            int totalGames = games.size();
            double avgPoints = games.stream().mapToInt(Game::getPoints).average().orElse(0.0);
            double avgRebounds = games.stream().mapToInt(Game::getRebounds).average().orElse(0.0);
            double avgAssists = games.stream().mapToInt(Game::getAssists).average().orElse(0.0);

            avgPoints = BigDecimal.valueOf(avgPoints).setScale(2, RoundingMode.HALF_UP).doubleValue();
            avgRebounds = BigDecimal.valueOf(avgRebounds).setScale(2, RoundingMode.HALF_UP).doubleValue();
            avgAssists = BigDecimal.valueOf(avgAssists).setScale(2, RoundingMode.HALF_UP).doubleValue();

            // Placeholder values for workouts, replace with actual logic if you have a WorkoutRepository
            int totalWorkouts = 0;
            int weeklyWorkouts = 0;

            DashboardResponse response = new DashboardResponse();
            response.setTotalGames(totalGames);
            response.setAvgPoints(avgPoints);
            response.setAvgRebounds(avgRebounds);
            response.setAvgAssists(avgAssists);
            response.setTotalWorkouts(totalWorkouts);
            response.setWeeklyWorkouts(weeklyWorkouts);
            return response;
        } catch (Exception e) {
            log.error("Failed to fetch dashboard data: {}", e.getMessage(), e);
            throw new DashboardException("Could not retrieve dashboard data", e);
        }
    }
}
