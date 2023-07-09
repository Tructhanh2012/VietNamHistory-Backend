package com.second.version.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class NumberResponse {
    long quantity;

    public NumberResponse(long quantity) {
        this.quantity = quantity;
    }
}
