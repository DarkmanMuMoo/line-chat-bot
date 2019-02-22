package com.mumoo.controller;

import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.mumoo.Application;
import com.mumoo.model.User;
import com.mumoo.service.AuthenticationService;
import com.mumoo.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



@LineMessageHandler
public class WebHookController {

    private TodoService todoService;
    private AuthenticationService authenticationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    public WebHookController(TodoService todoService,AuthenticationService authenticationService) {
        this.todoService = todoService;
        this.authenticationService = authenticationService;
    }

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {

        try{
            User sender = authenticationService.getOrCreateUserFromLineID(event.getSource().getUserId()).block();
            String  command = event.getMessage().getText();
            String replyMsg = "hi";
            todoService.createTaskFromTextCommand(command,sender.getId());
            return new TextMessage(replyMsg);
        }catch(Exception exception){
            LOGGER.error("wtf",exception);
            return new TextMessage("งง");
        }

    }

    @EventMapping
    public void log(FollowEvent event){

        LOGGER.info("got event "+ event.toString());

    }
}
