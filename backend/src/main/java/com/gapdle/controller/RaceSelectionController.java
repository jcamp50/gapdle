package com.gapdle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.gapdle.service.RaceService;
import com.gapdle.model.Race;

@RestController
@RequestMapping("/api/daily-race")

public class RaceSelectionController {

    private final RaceService raceService;

    @Autowired
    public RaceSelectionController(RaceService raceService) {
        this.raceService = raceService;
    }

    @PostMapping("/select")
    public ResponseEntity<String> selectDailyRace(@RequestParam Long raceId) {
        Race selectedRace = raceService.selectDailyRace(raceId);
        return ResponseEntity.ok("Daily race selected: " + selectedRace.getId());
    }

    @GetMapping("/most-voted")
    public ResponseEntity<Race> getMostVotedRace() {
        Race topRace = raceService.getMostVotedRace();
        return ResponseEntity.ok(topRace);
    }
}
