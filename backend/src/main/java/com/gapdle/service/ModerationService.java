package com.gapdle.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gapdle.model.*;
import com.gapdle.repository.SubmissionRepository;
import com.gapdle.repository.RaceRepository;
import com.gapdle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ModerationService {

    private final SubmissionRepository submissionRepository;
    private final RaceService raceService;
    private final CarService carService;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Autowired
    public ModerationService(SubmissionRepository submissionRepository, RaceService raceService, CarService carService, ObjectMapper objectMapper, UserRepository userRepository) {
        this.submissionRepository = submissionRepository;
        this.raceService = raceService;
        this.carService = carService;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    public Submission getPendingSubmission(Long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Submission not found"));
    }

    public void approveSubmission(Long id, Long moderatorId) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Submission with ID " + id + " not found."));

        User moderator = userRepository.findById(moderatorId)
                .orElseThrow(() -> new RuntimeException("Moderator not found"));

        User submittedBy = submission.getSubmittedBy();

        System.out.println("Found submitted by " + submittedBy + " for moderator " + moderator);

        try {
            Car car1 = objectMapper.readValue(submission.getCar1Details(), Car.class);
            Car car2 = objectMapper.readValue(submission.getCar2Details(), Car.class);

            car1 = carService.getOrCreateCar(car1);
            car2 = carService.getOrCreateCar(car2);

            Car winner;
            if ("car1".equalsIgnoreCase(submission.getWinner())) {
                winner = car1;
            } else if ("car2".equalsIgnoreCase(submission.getWinner())) {
                winner = car2;
            } else {
                throw new IllegalArgumentException("Invalid winner specified: " + submission.getWinner());
            }

            // Use RaceService to create race
            Race createdRace = raceService.createRace(
                    submission.getVideoUrl(), car1, car2, winner, moderator, submittedBy
            );

            submission.setRace(createdRace);
            submission.setStatus("approved");
            submissionRepository.save(submission);

        } catch (Exception e) {
            throw new RuntimeException("Error processing approval", e);
        }
    }

    public void rejectSubmission(Long id, Long moderatorId, String reason) {
        Optional<Submission> submissionOpt = submissionRepository.findById(id);

        User moderator = userRepository.findById(moderatorId)
                .orElseThrow(() -> new RuntimeException("Moderator not found"));

        if (submissionOpt.isPresent()) {
            Submission submission = submissionOpt.get();

            submission.setRejectedBy(moderator);
            submission.setStatus("rejected");
            submission.setRejectionReason(reason);
            submissionRepository.save(submission);
        }
    }

}
