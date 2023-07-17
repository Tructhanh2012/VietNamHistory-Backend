package com.second.version.controller;

import com.second.version.dto.request.CreateResultRequest;
import com.second.version.dto.request.IdRequest;
import com.second.version.question.QuestionEntity;
import com.second.version.question.QuestionService;
import com.second.version.result.ResultEntity;
import com.second.version.result.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/member")
@PreAuthorize("hasRole('MEMBER')")
@AllArgsConstructor

public class MemberController {
    private QuestionService questionService;
    private ResultService resultService;
    //user làm quiz
    @PostMapping("/create-quiz")
    public ResponseEntity<?> createQuiz(@RequestBody IdRequest idRequest){
        List<QuestionEntity> quiz = questionService.createQuiz(idRequest.getId());
        return ResponseEntity.ok(quiz);
    }
    @PostMapping("/create-result")
    public ResponseEntity<?> createResult(@RequestBody CreateResultRequest createResultRequest){
        ResultEntity resultEntity = resultService.createResult(createResultRequest);
        return ResponseEntity.ok(resultEntity);
    }
    @PostMapping("/results")
    public ResponseEntity<?> getMemberResultById(@RequestBody IdRequest idRequest){
        List<ResultEntity> resultEntities = resultService.getResults(idRequest.getId());
        return ResponseEntity.ok(resultEntities);
    }
}
