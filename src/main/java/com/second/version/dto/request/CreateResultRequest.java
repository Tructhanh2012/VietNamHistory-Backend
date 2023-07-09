package com.second.version.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateResultRequest {
    long memberId;
    int point;
}
