package com.main.controller;

import com.main.model.TopicModel;
import com.main.service.TopicsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicController {
    private final TopicsService topicsService;

    public TopicController(TopicsService topicsService) {
        this.topicsService = topicsService;
    }

    @PostMapping("/topics")
    public TopicModel createTopic(@RequestBody TopicModel topicModel) {
        return topicsService.createTopic(topicModel);
    }

    @GetMapping("/topics")
    public List<TopicModel> getAllTopics() {
        return topicsService.getAllTopics();
    }

    @GetMapping("/topic/id")
    public TopicModel getTopicById(@RequestParam("topicId") Long topicId) {
        return topicsService.getTopicById(topicId);
    }

    @PutMapping("/topic/id")
    public TopicModel updateTopic(@RequestBody TopicModel topicModel, @RequestParam("topicId") Long topicId) {
        return topicsService.updateTopic(topicModel, topicId);
    }

    @DeleteMapping("/topic/id")
    public String deleteTopicById(@RequestParam("topicId") Long topicId) {
        return topicsService.deleteTopic(topicId);
    }
}
