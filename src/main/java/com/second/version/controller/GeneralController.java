package com.second.version.controller;

import com.second.version.article.ArticleEntity;
import com.second.version.article.ArticleRepo;
import com.second.version.article.ArticleService;
import com.second.version.dto.request.IdRequest;
import com.second.version.dto.request.UpdateProfileRequest;
import com.second.version.dto.response.RankResponse;
import com.second.version.hashtag.HashtagEntity;
import com.second.version.hashtag.HashtagRepo;
import com.second.version.result.ResultRepo;
import com.second.version.result.ResultService;
import com.second.version.user.UserEntity;
import com.second.version.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/general")
@AllArgsConstructor
public class GeneralController {
    private ArticleService articleService;
    private HashtagRepo hashtagRepo;
    private ResultService resultService;
    private UserService userService;
    private ArticleRepo articleRepo;

    @PostMapping("/articles-hashtag")
    public ResponseEntity<?> getArticlesByHashtag(@RequestBody IdRequest idRequest) {
        List<ArticleEntity> list = articleService.getArticlesByHashtag(idRequest.getId());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/hashtags")
    public ResponseEntity<?> getAllHashtag() {
        List<HashtagEntity> hashtagEntities = hashtagRepo.findAll();
        return ResponseEntity.ok(hashtagEntities);
    }

    @GetMapping("/top-member")
    public ResponseEntity<?> getTopRank() {
        List<RankResponse> topRank = resultService.getTop();
        return ResponseEntity.ok(topRank);
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest updateProfileRequest) {
        UserEntity user = userService.editUser(updateProfileRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/article")
    public ResponseEntity<?> getSingleArticle(@RequestBody IdRequest idRequest){
        ArticleEntity article = articleRepo.findById(idRequest.getId()).orElseThrow();
        return ResponseEntity.ok(article);
    }

    //get list article
    @GetMapping("/list-articles")
    public ResponseEntity<?> getListArticle() {
        List<ArticleEntity> articleEntity = articleService.getAllArticles();
        return  ResponseEntity.ok(articleEntity);
    }

}
