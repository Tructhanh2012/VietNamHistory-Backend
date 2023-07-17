package com.second.version.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGeographicArticleRequest {
    long provinceId;
    List<Long> articleId;
}
