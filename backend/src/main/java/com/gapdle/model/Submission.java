package com.gapdle.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "submissions")
@Getter
@Setter
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "video_url", nullable = false)
    private String videoUrl;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id")
    private Race race;

    @ManyToOne
    @JoinColumn(name = "submitted_by", nullable = false)
    private User submittedBy;

    @Column(name = "status", nullable = false)
    private String status; //pending, approved, rejected

    @Column(name = "car1_details", columnDefinition = "TEXT")
    private String car1Details;

    @Column(name = "car2_details", columnDefinition = "TEXT")
    private String car2Details;

    @Column(name = "rejection_reason", columnDefinition = "TEXT")
    private String rejectionReason;

    @ManyToOne
    @JoinColumn(name = "rejected_by")
    private User rejectedBy;

    @Column(nullable = false)
    private String winner;

    @Column(name = "storage_type", nullable = false)
    private String storageType;

    @Column(name = "submitted_at", updatable = false, insertable = false)
    private Timestamp submittedAt;

}
