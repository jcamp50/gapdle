package com.gapdle.controller;

import com.gapdle.service.ModerationService;
import com.gapdle.model.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RestController
@RequestMapping("/api/moderation")

public class  ModerationController {

    private final ModerationService moderationService;

    @Autowired
    public ModerationController(ModerationService moderationService) {
        this.moderationService = moderationService;
    }

    @GetMapping("/pending/{id}")
    public ResponseEntity<Submission> getPendingSubmission(@PathVariable Long id) {
        Submission submission = moderationService.getPendingSubmission(id);
        return ResponseEntity.ok(submission);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<String> approveSubmission(
            @PathVariable Long id,
            @RequestParam Long moderatorId) {

        moderationService.approveSubmission(id, moderatorId);
        return ResponseEntity.ok("Submission approved by moderator " + moderatorId);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<String> rejectSubmission(
            @PathVariable Long id,
            @RequestParam Long moderatorId,
            @RequestBody String reason) {
        moderationService.rejectSubmission(id, moderatorId, reason);
        return ResponseEntity.ok("Submission rejected");
    }

}
