package com.gapdle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.gapdle.service.RaceVoteService;


@RestController
@RequestMapping("/api/votes")
public class RaceVoteController {

    private final RaceVoteService raceVoteService;

    @Autowired
    public RaceVoteController(RaceVoteService raceVoteService) {
        this.raceVoteService = raceVoteService;
    }

    @PostMapping("/vote")
    public ResponseEntity<String> voteForRace(@RequestParam Long raceId, @RequestParam Long moderatorId) {
        String message = raceVoteService.voteForRace(raceId, moderatorId);
        return ResponseEntity.ok(message);
    }
}
