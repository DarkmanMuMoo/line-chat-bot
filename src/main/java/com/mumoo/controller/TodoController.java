package com.mumoo.controller;

import com.mumoo.model.Task;
import com.mumoo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @PatchMapping("{id}")
    public Mono<Task> markDone(@PathVariable Long id) throws Exception {

        return service.markDone(id, null);
    }


}


