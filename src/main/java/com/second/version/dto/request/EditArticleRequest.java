package com.second.version.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EditArticleRequest {
    long articleId;
    long hashtagId;
    String title;
    String image;
    @Column(length = 10000)
    String content;
    Long provinceId;
}
