package org.example.testing.dto;

public class GameRequest {
    private String date;
    private int points;
    private int rebounds;
    private int assists;

    // Getters and setters
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
    public int getRebounds() { return rebounds; }
    public void setRebounds(int rebounds) { this.rebounds = rebounds; }
    public int getAssists() { return assists; }
    public void setAssists(int assists) { this.assists = assists; }
}