package com.mumoo.controller;

import com.mumoo.model.Task;
import com.mumoo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private TodoService service;

    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping()
    public Flux<Task> getTaskListForUser() {
        return service.getTaskListForUser(1L);
    }

}


