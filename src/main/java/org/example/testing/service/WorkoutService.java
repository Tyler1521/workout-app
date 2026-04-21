package org.example.testing.service;

import org.example.testing.dto.WorkoutRequest;
import org.example.testing.dto.ExerciseRequest;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {
    public String addWorkout(WorkoutRequest request) {
        // Business logic to save workout
        return "Workout added: " + request.getDate();
    }

    public String addExercise(Long workoutId, ExerciseRequest request) {
        // Business logic to add exercise to workout
        return "Exercise added to workout " + workoutId + ": " + request.getName();
    }
}