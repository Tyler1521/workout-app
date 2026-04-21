package org.example.testing.dto;

public class ExerciseRequest {
    private String name;
    private int weight;
    private int reps;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }
    public int getReps() { return reps; }
    public void setReps(int reps) { this.reps = reps; }
}