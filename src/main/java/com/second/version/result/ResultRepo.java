package com.second.version.result;

import com.second.version.dto.response.RankResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepo extends JpaRepository<ResultEntity, Long> {
    List<ResultEntity> findResultEntitiesByMember_Id(long id);

    @Query("SELECT new com.second.version.dto.response.RankResponse(r.member.id, r.member.name, sum(r.point), count(r.member.id)) from ResultEntity r group by r.member.id")
    List<RankResponse> getTopMember();
}
