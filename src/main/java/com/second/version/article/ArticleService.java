package com.second.version.article;

import com.second.version.dto.request.CreateArticleRequest;
import com.second.version.dto.request.EditArticleRequest;
import com.second.version.dto.response.ArticleByEditor;
import com.second.version.hashtag.HashtagEntity;
import com.second.version.hashtag.HashtagRepo;
import com.second.version.province.ProvinceEntity;
import com.second.version.province.ProvinceRepo;
import com.second.version.user.UserEntity;
import com.second.version.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {
    private ArticleRepo articleRepo;
    private UserRepository userRepository;
    private HashtagRepo hashtagRepo;
    private ProvinceRepo provinceRepo;
    public ArticleEntity createArticle(CreateArticleRequest request) {
        ArticleEntity articleEntity = articleRepo.findArticleEntityByTitle(request.getTitle());
        if (articleEntity == null) {
            UserEntity user = userRepository.findById(request.getEditorId());
            HashtagEntity hashtag = hashtagRepo.findById(request.getHashtagId()).orElseThrow();
            ProvinceEntity province = null;
            if (request.getProvinceId() != null) {
                province = provinceRepo.findById(request.getProvinceId()).orElseThrow();
            }
            ArticleEntity article = new ArticleEntity(hashtag, user, request.getTitle(), request.getImage(),
                                    request.getContent(), request.getDate(), request.getMonth(), province);
            articleRepo.save(article);
            return article;
        }
        return null;
    }

    ///
    public List<ArticleEntity> getAllArticles() {
        return articleRepo.findAll();
    }
    ///


    public List<ArticleEntity> getArticlesByHashtag(long id) {
        List<ArticleEntity> list = articleRepo.findArticleEntitiesByHashtagEntity_Id(id);
        return list;
    }

    public List<ArticleEntity> getArticlesByEditor(long id) {
        List<ArticleEntity> list = articleRepo.findArticleEntitiesByEditor_Id(id);
        return list;
    }


    public void deleteArticle(long id) {
        articleRepo.deleteById(id);
    }

    public ArticleEntity editArticle(EditArticleRequest request) {
        ArticleEntity articleEntity = articleRepo.findById(request.getArticleId()).orElseThrow();
        if (articleEntity != null) {
            HashtagEntity hashtag = hashtagRepo.findById(request.getHashtagId()).orElseThrow();

            if (articleRepo.findByTitleAndIdNot(request.getTitle(),request.getArticleId()) == null)
                articleEntity.setTitle(request.getTitle());
            else return null;

            Long provinceId = request.getProvinceId();
            if (provinceId != null) {
                ProvinceEntity province = provinceRepo.findById(provinceId).orElseThrow();
                articleEntity.setProvince(province);
            } else articleEntity.setProvince(null);

            articleEntity.setHashtagEntity(hashtag);
            articleEntity.setContent(request.getContent());
            articleEntity.setImage(request.getImage());
        }
        return articleRepo.save(articleEntity);
    }

    public List<ArticleByEditor> getListArticleByEditor(long id) {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate lastDayOfWeek = firstDayOfWeek.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        int monday = firstDayOfWeek.getDayOfMonth();
        int sunday = lastDayOfWeek.getDayOfMonth();
        int month = firstDayOfWeek.getMonthValue();
        return articleRepo.getListArticleByEditor(id, month, monday, sunday);
    }

    public List<ArticleByEditor> getListArticleByAdmin() {
        return articleRepo.getListArticleByAdmin();
    }


}
