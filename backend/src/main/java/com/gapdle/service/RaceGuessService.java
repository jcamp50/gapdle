package com.gapdle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gapdle.model.RaceVote;
import com.gapdle.repository.RaceGuessRepository;
import com.gapdle.repository.RaceRepository;
import com.gapdle.repository.CarRepository;
import com.gapdle.repository.UserRepository;

import com.gapdle.model.Race;
import com.gapdle.model.User;
import com.gapdle.model.RaceGuess;
import com.gapdle.model.Car;

import java.util.*;


@Service
public class RaceGuessService {


    private final RaceGuessRepository raceGuessRepository;


    private  final RaceRepository raceRepository;


    private final UserRepository userRepository;


    private final CarRepository carRepository;

    @Autowired
    public RaceGuessService(RaceGuessRepository raceGuessRepository, UserRepository userRepository, CarRepository carRepository, RaceRepository raceRepository) {
        this.raceGuessRepository = raceGuessRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;

    }

    public String submitGuess(Long userId, Long raceId, Long guessedWinnerId) {
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new RuntimeException("Race not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Car guessedWinner = carRepository.findById(guessedWinnerId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        RaceGuess guess = new RaceGuess();
        guess.setRace(race);
        guess.setUser(user);
        guess.setGuessedWinner(guessedWinner);

        raceGuessRepository.save(guess);
        return "Guess recorded successfully.";
    }

    public Map<String, Long> getGuessStats(Long raceId) {
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new RuntimeException("Race not found"));

        Map<String, Long> guessStats = new HashMap<>();
        for (Car car : Arrays.asList(race.getCar1(), race.getCar2())) {
            long count = raceGuessRepository.countByRaceAndGuessedWinner(race, car);
            guessStats.put(car.getMake() + " " + car.getModel(), count);
        }

        return guessStats;
    }
}
