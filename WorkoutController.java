package org.example.testing.controller;

import org.example.testing.dto.WorkoutRequest;
import org.example.testing.dto.ExerciseRequest;
import org.example.testing.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Operation(
    summary = "Some JWT-protected endpoint",
    security = @SecurityRequirement(name = "bearerAuth")
)
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping
    public String addWorkout(@RequestBody WorkoutRequest request) {
        return workoutService.addWorkout(request);
    }

    @PostMapping("/{id}/exercises")
    public String addExercise(@PathVariable Long id, @RequestBody ExerciseRequest request) {
        return workoutService.addExercise(id, request);
    }
}