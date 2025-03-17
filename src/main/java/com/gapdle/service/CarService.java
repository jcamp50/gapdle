package com.gapdle.service;

import com.gapdle.model.Car;
import com.gapdle.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car getOrCreateCar(Car car) {
        Optional<Car> existingCar;

        if (car.getIsStock()) {
            existingCar = carRepository.findByMakeAndModelAndYearAndTrimAndColor(
                    car.getMake(), car.getModel(), car.getYear(), car.getTrim(), car.getColor()
            );
        } else {
            existingCar = carRepository.findByMakeAndModelAndYearAndTrimAndEngineAndHorsepowerAndWeightAndTorqueAndColor(
                    car.getMake(), car.getModel(), car.getYear(), car.getTrim(),
                    car.getEngine(), car.getHorsepower(), car.getWeight(),
                    car.getTorque(), car.getColor()
            );
        }

        return existingCar.orElse(carRepository.save(car));

    }
}
