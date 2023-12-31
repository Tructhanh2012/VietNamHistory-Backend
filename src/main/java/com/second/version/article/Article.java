package com.second.version.article;

import com.second.version.event.Event;
import com.second.version.generation.Generation;
import com.second.version.hashtag.HashtagEntity;
import com.second.version.province.ProvinceEntity;
import com.second.version.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "hashtag_id")
    private HashtagEntity hashtagEntity;

    @ManyToOne
    @JoinColumn(name = "editor_id")
    private UserEntity editor;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci") // Added nullable = false to enforce non-nullability
    private String title;
    @Lob
    @Column(nullable = false, length = 2000, columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String image;

    @Lob
    @Column(nullable = false, length = 20000, columnDefinition = "TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci") // Adjusted length based on your requirements
    private String content;

    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    private ProvinceEntity province;

    private int date;
    private int month;

    @ManyToOne(optional = false)
    @JoinColumn(name = "generation_id", nullable = false, columnDefinition = "DEFAULT 1")
    private Generation generation;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public ArticleEntity(HashtagEntity hashtag, UserEntity editor, ProvinceEntity province, String title, String content, String image, int date, int month, Generation generation, Event event){
        this.hashtagEntity = hashtag;
        this.editor = editor;
        this.province = province;
        this.title = title;
        this.content = content;
        this.image = image;
        this.date = date;
        this.month = month;
        this.generation = generation;
        this.event = event;
    }

    public ArticleEntity(HashtagEntity hashtag, UserEntity editor, String title, String image, String content, int date, int month, ProvinceEntity province) {
        this.hashtagEntity = hashtag;
        this.editor = editor;
        this.province = province;
        this.title = title;
        this.image = image;
        this.content = content;
        this.date = date;
        this.month = month;
    }
}