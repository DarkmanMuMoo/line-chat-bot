package com.mumoo.util;

import com.mumoo.model.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TodoUtilTest {


    @Test
    public void parseMessage_success() {


        Task result  = TodoUtil.parseMessage("Buy milk : 2/5/18 : 13:00",1L);

        assertEquals(result.getTitle(),"Buy milk");
        assertEquals(result.getCreated(), LocalDateTime.of(2018,5,2,13,0));
        assertEquals((long)result.getUserId(),1L);
    }

    @Test
    public void parseMessage_shouldUnderstandToday() {


        Task result  = TodoUtil.parseMessage("Buy milk : today : 13:00",1L);

        assertEquals(result.getTitle(),"Buy milk");
        assertEquals(result.getCreated(), LocalDateTime.of(LocalDate.now(), LocalTime.of(13,0)));
        assertEquals((long)result.getUserId(),1L);
    }

    @Test
    public void parseMessage_shouldUnderstandTomorrow() {

        Task result  = TodoUtil.parseMessage("Buy milk : tomorrow : 13:00",1L);

        assertEquals(result.getTitle(),"Buy milk");
        assertEquals(result.getCreated(), LocalDateTime.of(LocalDate.now().plusDays(1L), LocalTime.of(13,0)));
        assertEquals((long)result.getUserId(),1L);
    }

    @Test
    public void parseMessage_shouldPutDefaultTime() {


        Task result  = TodoUtil.parseMessage("Buy milk : 2/5/18 ",1L);

        assertEquals(result.getTitle(),"Buy milk");
        assertEquals(result.getCreated(), LocalDateTime.of(2018,5,2,12,0));
        assertEquals((long)result.getUserId(),1L);
    }



}