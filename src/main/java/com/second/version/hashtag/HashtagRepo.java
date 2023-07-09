package com.second.version.hashtag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepo extends JpaRepository<HashtagEntity, Long> {

}
