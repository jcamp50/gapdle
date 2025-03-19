package com.gapdle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gapdle.model.RaceVote;
import com.gapdle.repository.RaceVoteRepository;
import com.gapdle.repository.RaceRepository;
import com.gapdle.repository.UserRepository;
import com.gapdle.model.Race;
import com.gapdle.model.User;



@Service
public class RaceVoteService {


    private final RaceVoteRepository raceVoteRepository;

    private final RaceRepository raceRepository;

    private final UserRepository userRepository;

    @Autowired
    public RaceVoteService(RaceVoteRepository raceVoteRepository, RaceRepository raceRepository, UserRepository userRepository) {
        this.raceVoteRepository = raceVoteRepository;
        this.raceRepository = raceRepository;
        this.userRepository = userRepository;

    }

    public String voteForRace(Long raceId, Long moderatorId) {
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new RuntimeException("Race not found"));

        User moderator = userRepository.findById(moderatorId)
                .orElseThrow(() -> new RuntimeException("Moderator not found"));

        if (raceVoteRepository.existsByRaceAndModerator(race, moderator)) {
            return "You have already voted for this race.";
        }

        RaceVote vote = new RaceVote();
        vote.setRace(race);
        vote.setModerator(moderator);
        raceVoteRepository.save(vote);
        return "Vote recorded successfully.";
    }
}

