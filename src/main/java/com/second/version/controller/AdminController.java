package com.second.version.controller;

import com.second.version.article.ArticleRepo;
import com.second.version.article.ArticleService;
import com.second.version.dto.request.CreateEditorRequest;
import com.second.version.dto.request.IdRequest;
import com.second.version.question.QuestionRepo;
import com.second.version.user.UserEntity;
import com.second.version.user.UserRepository;
import com.second.version.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class AdminController {
    private UserService userService;
    private UserRepository userRepository;

    private ArticleService articleService;
    private ArticleRepo articleRepo;
    private QuestionRepo questionRepo;

    @PostMapping("/create-editor")
    public ResponseEntity<?> createEditor(@RequestBody CreateEditorRequest createEditorRequest) {
        UserEntity user = userService.createEditor(createEditorRequest);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser(@RequestBody IdRequest idRequest) {
        userService.deleteById(idRequest.getId());
        return ResponseEntity.ok("Delete success");
    }

    @GetMapping("/members")
    public ResponseEntity<?> getAllMember() {
        return ResponseEntity.ok(userRepository.getAllMembers());
    }

    @GetMapping("/editors")
    public ResponseEntity<?> getAllEditor() {
        return ResponseEntity.ok(userRepository.getAllEditors());
    }

    @GetMapping("/articles")
    public ResponseEntity<?> getAllArticle() {
        return ResponseEntity.ok(articleService.getListArticleByAdmin());
    }
    @GetMapping("/member-quantity")
    public ResponseEntity<?> getNumberMember(){
        return ResponseEntity.ok(userRepository.getCountMembers());
    }

    @GetMapping("/editor-quantity")
    public ResponseEntity<?> getNumberEditor(){
        return ResponseEntity.ok(userRepository.getCountEditors());
    }

    @GetMapping("/article-quantity")
    public ResponseEntity<?> getNumberArticle(){
        return ResponseEntity.ok(articleRepo.getCountArticles());
    }
    @GetMapping("/question-quantity")
    public ResponseEntity<?> getNumberQuestion(){
        return ResponseEntity.ok(questionRepo.getNumberQuestion());
    }

}
