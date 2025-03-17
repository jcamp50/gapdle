package com.gapdle.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cars")
@Getter
@Setter

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column
    private String trim;

    @Column(nullable = false)
    private Integer year;

    @Column(name = "is_stock")
    private Boolean isStock;

    @Column(nullable = false)
    private String engine;

    @Column(nullable = false)
    private Integer horsepower;

    @Column(nullable = false)
    private Integer weight;

    @Column
    private Integer torque;

    @Column
    private String drivetrain;

    @Column
    private String color;

    @Column
    private String image_url;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Timestamp createdAt;

}
