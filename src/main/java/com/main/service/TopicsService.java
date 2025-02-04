package com.main.service;

import com.main.model.TopicModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TopicsService {
    TopicModel createTopic(TopicModel topicModel);
    List<TopicModel> getAllTopics();
    TopicModel getTopicById(Long topicId);
    TopicModel updateTopic(TopicModel topicModel,Long topicId);
    String deleteTopic(Long topicId);
}
