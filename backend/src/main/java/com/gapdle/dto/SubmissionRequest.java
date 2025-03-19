package com.gapdle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmissionRequest {
    private String videoUrl;
    private Integer submittedBy;
    private String storageType;
    private String winner;
    private CarDetails car1;
    private CarDetails car2;
    private String rejectionReason;
}
