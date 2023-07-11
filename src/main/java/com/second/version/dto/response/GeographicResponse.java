package com.second.version.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeographicResponse {
    long id;
    String name;
    String description;
    String image;
    List<ArticleInfoResponse> articleInfoResponses;
}
