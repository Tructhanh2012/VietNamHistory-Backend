package com.second.version.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionCreateRequest {
    String question;
    String firstChoice;
    String secondChoice;
    String thirdChoice;
    String answer;
    long hashtagId;
}
