package org.example.testing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "games")
public class Game {
    @Id
    private String id;
    private String date;
    private int points;
    private int rebounds;
    private int assists;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
    public int getRebounds() { return rebounds; }
    public void setRebounds(int rebounds) { this.rebounds = rebounds; }
    public int getAssists() { return assists; }
    public void setAssists(int assists) { this.assists = assists; }
}