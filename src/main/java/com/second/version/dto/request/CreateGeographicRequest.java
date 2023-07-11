package com.second.version.dto.request;

import com.second.version.dto.response.ArticleInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGeographicRequest {
    long provinceId;
    String description;
    String image;
    List<Long> articleId;
}
