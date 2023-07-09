package com.second.version.result;

import com.second.version.dto.request.CreateResultRequest;
import com.second.version.dto.response.RankResponse;
import com.second.version.user.UserEntity;
import com.second.version.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class ResultService {
    private ResultRepo resultRepo;
    private UserRepository userRepository;
    public ResultEntity createResult(CreateResultRequest createResultRequest){
        UserEntity member = userRepository.findById(createResultRequest.getMemberId());
        ResultEntity resultEntity = resultRepo.save(new ResultEntity(member, createResultRequest.getPoint()));
        return resultEntity;
    }
    public List<ResultEntity> getResults(long id){
        return resultRepo.findResultEntitiesByMember_Id(id);
    }
    public List<RankResponse> getTop(){
        List<RankResponse> topRank = resultRepo.getTopMember();
        Collections.sort(topRank, Comparator.comparingLong(RankResponse::getTotalPoint).reversed());
        return topRank;
    }
}
