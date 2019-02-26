package com.mumoo.repository;

import com.mumoo.model.Token;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends CrudRepository<Token, String> {
    @Query("select * from usertoken where userId = :userId")
    List<Token> findByUserId(@Param("userId") String userId);
}
