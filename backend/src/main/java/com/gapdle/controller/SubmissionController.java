package com.gapdle.controller;

import com.gapdle.model.Submission;
import com.gapdle.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.gapdle.dto.SubmissionRequest;



import java.util.List;

@RestController
@RequestMapping("/api/submissions")

public class SubmissionController {

    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping
    public ResponseEntity<Submission> createSubmission(@RequestBody SubmissionRequest request) {
        return ResponseEntity.ok(submissionService.createSubmission(request));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Submission>> getPendingSubmissions() {
        return ResponseEntity.ok(submissionService.getPendingSubmissions());
    }
}

