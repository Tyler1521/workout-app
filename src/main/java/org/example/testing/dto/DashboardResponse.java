package org.example.testing.dto;


import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended

    private int totalGames;
    private double avgPoints;
    private double avgRebounds;
    private double avgAssists;
    private int totalWorkouts;
    private int weeklyWorkouts;

    // Getters, Setters, and Constructors
}