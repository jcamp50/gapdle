package com.gapdle.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gapdle.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.gapdle.repository.SubmissionRepository;
import com.gapdle.model.Submission;
import com.gapdle.model.User;

import com.gapdle.dto.SubmissionRequest;
import com.gapdle.dto.CarDetails;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;

    @Autowired
    public SubmissionService(SubmissionRepository submissionRepository, UserRepository userRepository) {
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
    }

    public Submission createSubmission(SubmissionRequest request) {
        Submission submission = new Submission();

        User submitter = userRepository.findById(Long.valueOf(request.getSubmittedBy()))
                .orElseThrow(() -> new RuntimeException("User not found"));

        submission.setVideoUrl(request.getVideoUrl());
        submission.setSubmittedBy(submitter);
        submission.setStatus("pending");
        submission.setStorageType(request.getStorageType());
        submission.setWinner(request.getWinner());

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            submission.setCar1Details(objectMapper.writeValueAsString(request.getCar1()));
            submission.setCar2Details(objectMapper.writeValueAsString(request.getCar2()));

        }catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting car details to JSON");
        }

        return submissionRepository.save(submission);
    }

    public List<Submission> getPendingSubmissions() {
        return submissionRepository.findByStatus("pending");
    }


}
