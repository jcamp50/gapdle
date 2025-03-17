package com.gapdle.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gapdle.model.Car;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByMakeAndModelAndYearAndTrimAndColor(
            String make, String model, Integer year, String trim, String color
    );

    Optional<Car> findByMakeAndModelAndYearAndTrimAndEngineAndHorsepowerAndWeightAndTorqueAndColor(
            String make, String model, Integer year, String trim,
            String engine, Integer horsepower, Integer weight,
            Integer torque, String color
    );
}
