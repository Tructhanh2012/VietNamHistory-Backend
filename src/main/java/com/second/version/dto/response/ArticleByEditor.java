package com.second.version.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ArticleByEditor {
    long numberOfArticle;
    String hashtag;

    public ArticleByEditor(long numberOfArticle, String hashtag) {
        this.numberOfArticle = numberOfArticle;
        this.hashtag = hashtag;
    }
}
