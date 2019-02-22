package com.mumoo.service;

import com.mumoo.Application;
import com.mumoo.model.Task;
import com.mumoo.model.User;
import com.mumoo.repository.TaskRepository;
import com.mumoo.repository.UserRepository;
import com.mumoo.util.TodoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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


    public Mono<Task> createTaskFromTextCommand(String command ,Long userId){

       Task task =  TodoUtil.parseMessage(command,userId);
       LOGGER.info("got task"+ task);

      return Mono.just(taskRepository.save(task));
    }

    public Mono<Task> markDone(Long taskId,User user) throws Exception {

        Optional<Task> task = taskRepository.findById(taskId);

        TodoUtil.authorizeTask(task,user);

        Task updateTask = task.get();

        updateTask.setDone(true);

       return  Mono.just(taskRepository.save(updateTask));

    }

    public Mono<Task> markImportant(Long taskId,User user) throws Exception {
        Optional<Task> task = taskRepository.findById(taskId);
        TodoUtil.authorizeTask(task,user);

        Task updateTask = task.get();

        updateTask.setImportant(true);

        return  Mono.just(taskRepository.save(updateTask));

    }


}
