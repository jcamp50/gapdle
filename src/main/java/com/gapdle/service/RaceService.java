package com.gapdle.service;

import com.gapdle.model.Race;
import com.gapdle.model.Car;
import com.gapdle.model.User;

import com.gapdle.repository.RaceRepository;
import com.gapdle.repository.RaceVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class RaceService {

    private final RaceRepository raceRepository;

    private final RaceVoteRepository raceVoteRepository;

    @Autowired
    public RaceService(RaceRepository raceRepository, RaceVoteRepository raceVoteRepository) {
        this.raceRepository = raceRepository;
        this.raceVoteRepository = raceVoteRepository;
    }

    public Race getMostVotedRace() {
        List<Race> pending_races = raceRepository.findByStatus("pending");
        Race mostVotedRace = null;
        long maxVotes = 0;

        for (Race race : pending_races) {
            long voteCount = raceVoteRepository.countByRace(race);
            if (voteCount > maxVotes) {
                maxVotes = voteCount;
                mostVotedRace = race;
            }
        }
        return mostVotedRace;
    }

    public Race selectDailyRace(Long raceId) {
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new RuntimeException("Race not found"));

        race.setStatus("used");
        race.setSelectedForDate(Date.valueOf(LocalDate.now()));
        return raceRepository.save(race);
    }

    public Race createRace(String videoUrl, Car car1, Car car2, Car winner, User approvedBy, User submittedBy) {
        Race race = new Race();
        race.setVideoUrl(videoUrl);
        race.setCar1(car1);
        race.setCar2(car2);
        race.setWinnerId(winner);
        race.setStatus("pending"); // Races start in pending state
        race.setApprovedBy(approvedBy);
        race.setSubmittedBy(submittedBy);
        return raceRepository.save(race);
    }


    public List<Race> getPendingRaces(String status) {
        return raceRepository.findByStatus("pending");
    }
}
