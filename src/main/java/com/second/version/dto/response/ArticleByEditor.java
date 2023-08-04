package com.second.version.dto.response;

import lombok.Data;

@Data
public class ArticleByEditor {
    long numberOfArticle;
    String generation;

    public ArticleByEditor(long numberOfArticle, String generation) {
        this.numberOfArticle = numberOfArticle;
        this.generation = generation;
    }
}
