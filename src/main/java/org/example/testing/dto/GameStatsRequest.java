package org.example.testing.dto;

public class GameStatsRequest {
    private int totalGames;
    private double avgPoints;
    private double avgRebounds;
    private double avgAssists;

    // Getters and setters
    public int getTotalGames() { return totalGames; }
    public void setTotalGames(int totalGames) { this.totalGames = totalGames; }
    public double getAvgPoints() { return avgPoints; }
    public void setAvgPoints(double avgPoints) { this.avgPoints = avgPoints; }
    public double getAvgRebounds() { return avgRebounds; }
    public void setAvgRebounds(double avgRebounds) { this.avgRebounds = avgRebounds; }
    public double getAvgAssists() { return avgAssists; }
    public void setAvgAssists(double avgAssists) { this.avgAssists = avgAssists; }
}