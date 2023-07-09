package com.second.version.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RankResponse {
    long memberId;
    String name;
    long totalPoint;
    long numberOfQuiz;
}
