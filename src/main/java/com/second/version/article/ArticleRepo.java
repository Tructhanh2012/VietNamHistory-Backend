package com.second.version.article;

import com.second.version.dto.response.ArticleByEditor;
import com.second.version.dto.response.NumberResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ArticleRepo extends JpaRepository<ArticleEntity, Long> {
    List<ArticleEntity> findAll();
    ArticleEntity findByTitleAndIdNot(String title, long id);
    @Query("SELECT a from ArticleEntity a where a.title = :title")
    ArticleEntity findArticleEntityByTitle(@Param("title") String title);

    List<ArticleEntity> findArticleEntitiesByHashtagEntity_Id(long id);

    List<ArticleEntity> findArticleEntitiesByEditor_Id(long id);
    @Query("SELECT new com.second.version.dto.response.ArticleByEditor(count(a), a.hashtagEntity.name) from ArticleEntity a group by a.hashtagEntity.name")
    List<ArticleByEditor> getListArticleByAdmin();


    @Query("SELECT new com.second.version.dto.response.ArticleByEditor(count(a), a.hashtagEntity.name) from ArticleEntity a where a.editor.id = :editorId and a.month = :month and a.date between :monday AND :sunday group by a.hashtagEntity.name")
    List<ArticleByEditor> getListArticleByEditor(@Param("editorId") long editorId, @Param("month") int month, @Param("monday") int monday, @Param("sunday") int sunday);

    @Query("SELECT new com.second.version.dto.response.NumberResponse(count(a)) from ArticleEntity a")
    List<NumberResponse> getCountArticles();
}
