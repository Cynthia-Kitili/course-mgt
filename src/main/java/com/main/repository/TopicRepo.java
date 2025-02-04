package com.main.repository;

import com.main.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepo extends JpaRepository<TopicEntity, Long> {
}
