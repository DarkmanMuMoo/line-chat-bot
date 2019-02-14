package com.mumoo.service;

import com.mumoo.Application;
import com.mumoo.repository.TaskRepository;
import com.mumoo.model.Task;
import com.mumoo.model.User;
import com.mumoo.repository.UserRepository;
import com.mumoo.util.TodoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TodoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private TaskRepository taskRepository;

    private UserRepository userRepository;

    @Autowired
    public TodoService(TaskRepository taskRepository,UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Flux<Task> getTaskListForUser(Long userid){

        return  Flux.fromIterable(this.taskRepository.list());

    }

    public Mono<User> getUserFromLineID(String lineId){

      Optional<User> user =   this.userRepository.getByLineId(lineId);
      if(user.isEmpty()){
       return   Mono.just(this.userRepository.save(new User(lineId, LocalDateTime.now())));
      }

      return  Mono.just(user.get());


    }

    public Mono<Task> createTaskFromTextCommand(String command ,Long userId){

       Task task =  TodoUtil.parseMessage(command,userId);
       LOGGER.info("got task"+ task);

      return Mono.just(taskRepository.save(task));
    }


}
