package com.mumoo.repository;

import com.mumoo.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT * from userInfo where lineId = :lineId")
    Optional<User> getByLineId(@Param("lineId") String lineId);

}
