package com.second.version.controller;

import com.second.version.article.ArticleEntity;
import com.second.version.article.ArticleService;
import com.second.version.dto.request.CreateArticleRequest;
import com.second.version.dto.request.EditArticleRequest;
import com.second.version.dto.request.IdRequest;
import com.second.version.dto.request.QuestionCreateRequest;
import com.second.version.dto.response.ArticleByEditor;
import com.second.version.question.QuestionEntity;
import com.second.version.question.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/editor")
@PreAuthorize("hasRole('EDITOR')")
@AllArgsConstructor
public class EditorController {

    private ArticleService articleService;
    private QuestionService questionService;


    // cái này phải để ở general chứ
    // cái này làm gì v:v cái đó là viết r quên á:))


    @PostMapping("/create-article")
    public ResponseEntity<?> createArticle(@RequestBody CreateArticleRequest createArticleRequest){
        ArticleEntity articleEntity = articleService.createArticle(createArticleRequest);
        return ResponseEntity.ok(articleEntity);
    }

    @PostMapping("/articles-editor")
    public ResponseEntity<?> getArticlesByEditor(@RequestBody IdRequest idRequest){
        List<ArticleEntity> list = articleService.getArticlesByEditor(idRequest.getId());
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete-article")
    public ResponseEntity<?> deleteArticle(@RequestBody IdRequest idRequest){
        articleService.deleteArticle(idRequest.getId());
        return ResponseEntity.ok("Delete success");
    }

    @PutMapping("/edit-article")
    public ResponseEntity<?> editArticle(@RequestBody EditArticleRequest editArticleRequest){
       ArticleEntity articleEntity = articleService.editArticle(editArticleRequest);
       return ResponseEntity.ok(articleEntity);
    }

    @PostMapping("/create-questions")
    public ResponseEntity<?> createQuestions(@RequestBody List<QuestionCreateRequest> questionCreateRequests){
        List<QuestionEntity> questionEntityList = questionService.createQuestions(questionCreateRequests);
        return ResponseEntity.ok(questionEntityList);
    }

    @PostMapping("/articles-in-week")
    public ResponseEntity<?> getArticleInWeek(@RequestBody IdRequest idRequest){
        List<ArticleByEditor> list = articleService.getListArticleByEditor(idRequest.getId());
        return ResponseEntity.ok(list);
    }


}
