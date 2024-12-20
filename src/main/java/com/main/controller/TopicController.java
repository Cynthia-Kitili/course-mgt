package com.main.controller;

import com.main.model.TopicModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicController {

    @RequestMapping("/topics")
    public List<TopicModel> getTopics() {
        return Arrays.asList(
                new TopicModel("1","topic1","First Topic"),
                new TopicModel("2","topic2","Second Topic"),
                new TopicModel("3","topic3","Third Topic")
        );
    }
}
