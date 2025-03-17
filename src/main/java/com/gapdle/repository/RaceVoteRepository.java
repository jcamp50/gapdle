package com.gapdle.repository;


import com.gapdle.model.Race;
import com.gapdle.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gapdle.model.RaceVote;

@Repository
public interface RaceVoteRepository extends JpaRepository<RaceVote, Long> {
    long countByRace(Race race);
    boolean existsByRaceAndModerator(Race race, User moderator);
}
