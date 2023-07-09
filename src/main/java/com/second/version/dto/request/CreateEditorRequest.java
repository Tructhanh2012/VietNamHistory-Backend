package com.second.version.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CreateEditorRequest {
    String name;
    String email;
    String password;
}
