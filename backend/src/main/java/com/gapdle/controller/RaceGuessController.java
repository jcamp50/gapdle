package com.gapdle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.gapdle.service.RaceGuessService;

import java.util.Map;

@RestController
@RequestMapping("/api/guesses")
public class RaceGuessController {

    private final RaceGuessService raceGuessService;

    @Autowired
    public RaceGuessController(RaceGuessService raceGuessService) {
        this.raceGuessService = raceGuessService;
    }

    @PostMapping
    public ResponseEntity<String> submitGuess(
            @RequestParam Long userId,
            @RequestParam Long raceId,
            @RequestParam Long guessedWinnerId) {
        String message = raceGuessService.submitGuess(userId, raceId, guessedWinnerId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/stats/{raceId}")
    public ResponseEntity<Map<String, Long>> getGuessStats(@PathVariable Long raceId) {
        return ResponseEntity.ok(raceGuessService.getGuessStats(raceId));
    }
}

