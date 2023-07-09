package com.second.version.token;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {


    @Query("SELECT t FROM Token t INNER JOIN t.user u " +
            "WHERE u.id = :id AND (t.expired = false OR t.revoked = false)")
    Token findAllValidTokensByUser(Long id);

    Optional<Token> findByToken(String token);
    @Modifying
    @Transactional
    @Query("DELETE FROM Token t WHERE t.user.id = :id")
    void deleteTokenByUserId(@Param("id") long id);

}