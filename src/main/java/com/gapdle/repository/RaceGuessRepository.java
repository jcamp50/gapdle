package com.gapdle.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gapdle.model.Race;
import com.gapdle.model.RaceGuess;
import com.gapdle.model.Car;


@Repository
public interface RaceGuessRepository extends JpaRepository<RaceGuess, Long> {
    long countByRaceAndGuessedWinner(Race race, Car guessedWinner);
}
