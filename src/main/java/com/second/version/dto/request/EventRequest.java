package com.second.version.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CreateEventRequest {
    private String eventName;
    private String content;
    private String startYear;
    private String endYear;
    private Long generationId;
}
