package com.second.version.geographic;

import com.second.version.article.ArticleEntity;
import com.second.version.article.ArticleRepo;
import com.second.version.dto.request.CreateGeographicRequest;
import com.second.version.dto.response.ArticleInfoResponse;
import com.second.version.dto.response.GeographicResponse;
import com.second.version.province.ProvinceEntity;
import com.second.version.province.ProvinceRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GeographicService {
    GeographicRepo geographicRepo;

    ArticleRepo articleRepo;
ProvinceRepo provinceRepo;
    public List<GeographicResponse> getMapInfo() {
        List<GeographicEntity> geographicEntities = geographicRepo.findAll();
        List<GeographicResponse> responses = new ArrayList<>();

        for (GeographicEntity geographicEntity : geographicEntities) {
            GeographicResponse geographicResponse = new GeographicResponse();
            List<ArticleInfoResponse> articleInfoResponses = new ArrayList<>();

            for (long articleId : geographicEntity.getArticleId()) {
                ArticleEntity articleEntity = articleRepo.findById(articleId).orElseThrow();
                ArticleInfoResponse articleInfoResponse = new ArticleInfoResponse(articleEntity.getId(), articleEntity.getTitle());

                articleInfoResponses.add(articleInfoResponse);
            }

            geographicResponse.setId(geographicEntity.getId());
            geographicResponse.setArticleInfoResponses(articleInfoResponses);
            geographicResponse.setDescription(geographicEntity.getDescription());
            geographicResponse.setName(geographicEntity.getProvince().getName());
            geographicResponse.setImage(geographicEntity.getImage());
            responses.add(geographicResponse);
        }

        return responses;
    }


    public void createGeographic(CreateGeographicRequest createGeographicRequest) {
        ProvinceEntity provinceEntity = provinceRepo.findById(createGeographicRequest.getProvinceId()).orElseThrow();
        GeographicEntity geographicEntity = new GeographicEntity();
        geographicEntity.setDescription(createGeographicRequest.getDescription());
        geographicEntity.setImage(createGeographicRequest.getImage());
        geographicEntity.setProvince(provinceEntity);
        geographicEntity.setArticleId(createGeographicRequest.getArticleId());
        geographicRepo.save(geographicEntity);

    }
}
