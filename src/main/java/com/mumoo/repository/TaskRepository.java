package com.mumoo.repository;

import com.mumoo.model.Task;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query("select * from task order by important desc, created desc")
    List<Task> list();

}
