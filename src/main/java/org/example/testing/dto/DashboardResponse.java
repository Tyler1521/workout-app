package org.example.testing.dto;


import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {
    private int totalGames;
    private double avgPoints;
    private double avgRebounds;
    private double avgAssists;
    private int totalWorkouts;
    private int weeklyWorkouts;


}