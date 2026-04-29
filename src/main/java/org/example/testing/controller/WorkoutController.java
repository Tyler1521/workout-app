package org.example.testing.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.testing.dto.WorkoutRequest;
import org.example.testing.dto.ExerciseRequest;
import org.example.testing.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @Operation(
            summary = "Some JWT-protected endpoint",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping
    public String addWorkout(@RequestBody WorkoutRequest request) {
        return workoutService.addWorkout(request);
    }


    @Operation(
            summary = "Some JWT-protected endpoint",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/{id}/exercises")
    public String addExercise(@PathVariable Long id, @RequestBody ExerciseRequest request) {
        return workoutService.addExercise(id, request);
    }
}