package com.second.version.question;

import com.second.version.dto.request.QuestionCreateRequest;
import com.second.version.hashtag.HashtagEntity;
import com.second.version.hashtag.HashtagRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class QuestionService {
    private QuestionRepo questionRepo;
    private HashtagRepo hashtagRepo;
    public List<QuestionEntity> createQuestions(List<QuestionCreateRequest> questionCreateRequests){
        List<QuestionEntity> list = new ArrayList<>();
        HashtagEntity hashtag = hashtagRepo.findById(questionCreateRequests.get(0).getHashtagId()).orElseThrow();
        for(QuestionCreateRequest questionCreate: questionCreateRequests){
            QuestionEntity question = new QuestionEntity(questionCreate.getQuestion(), questionCreate.getFirstChoice(), questionCreate.getSecondChoice(), questionCreate.getThirdChoice(), questionCreate.getAnswer(), hashtag);
            list.add(questionRepo.save(question));
        }
        return list;
    }
    public static <T> List<T> getRandomElements(List<T> list, int count) {
        Random random = new Random();
        List<T> randomElements = new ArrayList<>();

        int size = list.size();
        if (count > size) {
            throw new IllegalArgumentException("Count is greater than the size of the list.");
        }

        for (int i = 0; i < count; i++) {
            int randomIndex = random.nextInt(size);
            randomElements.add(list.get(randomIndex));
            size--;
            list.set(randomIndex, list.get(size));
        }

        return randomElements;
    }
    public List<QuestionEntity> createQuiz(long id){
        List<QuestionEntity> questionEntityList = questionRepo.findRandomByHashtag(id);
        List<QuestionEntity> questionEntities = getRandomElements(questionEntityList, 5);
        return questionEntities;
    }
}
