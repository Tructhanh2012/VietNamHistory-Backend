package com.second.version.question;

import com.second.version.dto.response.ArticleByEditor;
import com.second.version.hashtag.HashtagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<QuestionEntity, Long> {
    @Query("select q from QuestionEntity q where q.hashtag.id = :hashtagId")
    List<QuestionEntity> findRandomByHashtag(@Param("hashtagId") Long hashtagId);
    @Query("SELECT new com.second.version.dto.response.ArticleByEditor(count(q), q.hashtag.name) from QuestionEntity q group by q.hashtag.name")
    List<ArticleByEditor> getNumberQuestion();
}
