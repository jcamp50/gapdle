package com.gapdle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDetails {
    private String make;
    private String model;
    private String trim;
    private Integer year;
    private Boolean isStock;
    private String engine;
    private Integer horsepower;
    private Integer weight;
    private String torque;
    private String color;
    private String drivetrain;
}
