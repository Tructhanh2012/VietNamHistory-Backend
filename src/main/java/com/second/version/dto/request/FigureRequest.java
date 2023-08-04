package com.second.version.dto.request;

import lombok.Data;

@Data
public class CreateFigureRequest {
    private String name;
    private String description;
    private String birthYear;
    private String passedYear;
    private Long generationId;
}
