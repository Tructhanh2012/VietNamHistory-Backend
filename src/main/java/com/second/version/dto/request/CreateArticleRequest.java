package com.second.version.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateArticleRequest {
    long editorId;
    long hashtagId;
    Long provinceId;
    String title;
    @Column(length = 10000)
    String image;
    @Column(length = 10000)
    String content;
    int date;
    int month;
}
