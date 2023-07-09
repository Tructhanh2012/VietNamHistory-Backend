package com.second.version.user;
import com.second.version.dto.response.NumberResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    UserEntity findById(long id);
    @Query("SELECT u from UserEntity u WHERE u.email = :email")
    UserEntity findByMail(@Param("email") String email);


    @Query("SELECT u from UserEntity u where u.role = com.second.version.user.Role.MEMBER")
    List<UserEntity> getAllMembers();

    @Query("SELECT u from UserEntity u where u.role = com.second.version.user.Role.EDITOR")
    List<UserEntity> getAllEditors();

    @Query("SELECT new com.second.version.dto.response.NumberResponse(count(u)) from UserEntity u where u.role = com.second.version.user.Role.EDITOR")
    List<NumberResponse> getCountEditors();

    @Query("SELECT new com.second.version.dto.response.NumberResponse(count(u)) from UserEntity u where u.role = com.second.version.user.Role.MEMBER")
    List<NumberResponse> getCountMembers();
}

