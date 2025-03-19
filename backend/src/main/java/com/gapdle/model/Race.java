package com.gapdle.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "races")
@Getter
@Setter

public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "video_url", nullable = false)
    private String videoUrl;

    @ManyToOne
    @JoinColumn(name = "car1_id", nullable = false)
    private Car car1;

    @ManyToOne
    @JoinColumn(name = "car2_id", nullable = false)
    private Car car2;

    @Column(name = "status")
    private String status;

    @Column(name = "selected_for_date")
    private Date selectedForDate;

    @ManyToOne
    @JoinColumn(name = "winner_id", nullable = false)
    private Car winnerId;

    @ManyToOne
    @JoinColumn(name = "approved_by", nullable = false)
    private User approvedBy;

    @ManyToOne
    @JoinColumn(name = "submitted_by", nullable = false)
    private User submittedBy;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Timestamp createdAt;
}
