package com.main.service.impl;

import com.main.entity.TopicEntity;
import com.main.model.TopicModel;
import com.main.repository.TopicRepo;
import com.main.service.TopicsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TopicsServiceImpl implements TopicsService {

    private final TopicRepo topicRepo;

    public TopicsServiceImpl(TopicRepo topicRepo) {
        this.topicRepo = topicRepo;
    }

    @Override
    public TopicModel createTopic(TopicModel topicModel) {
        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setTopicName(topicModel.getTopicName());
        topicEntity.setTopicDescription(topicModel.getTopicDescription());

        topicRepo.save(topicEntity);

        log.info("Saved Topic Entity: {}", topicEntity);

        TopicModel topicModel1 = new TopicModel();
        topicModel1.setTopicId(topicEntity.getTopicId());
        topicModel1.setTopicName(topicEntity.getTopicName());
        topicModel1.setTopicDescription(topicEntity.getTopicDescription());

        return topicModel1;
    }

    @Override
    public List<TopicModel> getAllTopics() {
        List<TopicEntity> topicEntities = topicRepo.findAll(); // Correctly retrieve a list of TopicEntity objects
        List<TopicModel> topicModels = new ArrayList<>();

        for (TopicEntity topicEntity : topicEntities) {
            TopicModel topicModel = new TopicModel();
            topicModel.setTopicId(topicEntity.getTopicId());
            topicModel.setTopicName(topicEntity.getTopicName());
            topicModel.setTopicDescription(topicEntity.getTopicDescription());
            topicModels.add(topicModel);
        }
        return topicModels;
    }

    @Override
    public TopicModel getTopicById(Long topicId) {
//        map topic entity in repo and find by id use optional
        Optional<TopicEntity> topicEntity = topicRepo.findById(topicId);

        TopicModel topicModel = new TopicModel();
//        if it exists return in model
        if (topicEntity.isPresent()) {
            topicModel.setTopicId(topicEntity.get().getTopicId());
            topicModel.setTopicName(topicEntity.get().getTopicName());
            topicModel.setTopicDescription(topicEntity.get().getTopicDescription());
            return topicModel;
        }else{
//        if it does not exist return json for missing
            topicModel.setTopicDescription("Missing topic");
        }
//        create method in controller and pass id as param or request body
        return topicModel;
    }

    @Override
    public TopicModel updateTopic(TopicModel topicModel,Long topicId) {
//        map Topic entity and get specific topic entity by id: use optional
        Optional<TopicEntity> optionalTopicEntity = topicRepo.findById(topicId);
//        if topic entity does not exist return missing
        TopicEntity topicEntity = optionalTopicEntity.orElseThrow(() -> new RuntimeException("Topic not found"));
//        get from topic model and set new values for entity
        topicEntity.setTopicId(topicId);
        topicEntity.setTopicName(topicModel.getTopicName());
        topicEntity.setTopicDescription(topicModel.getTopicDescription());
//        save to db
        topicRepo.save(topicEntity);
//        instantiate new TopicModel and return with new values from entity
        TopicModel topicModel1 = new TopicModel();
        topicModel1.setTopicId(topicEntity.getTopicId());
        topicModel1.setTopicName(topicEntity.getTopicName());
        topicModel1.setTopicDescription(topicEntity.getTopicDescription());

        return topicModel1;
    }

    @Override
    public String deleteTopic(Long topicId) {
//        find optional by id
        Optional<TopicEntity> optionalTopicEntity = topicRepo.findById(topicId);
        TopicEntity topicEntity = optionalTopicEntity.orElseThrow(() -> new RuntimeException("Topic not found"));
//        delete from db
        topicRepo.delete(topicEntity);
        return "Deleted successfully";
    }


}
