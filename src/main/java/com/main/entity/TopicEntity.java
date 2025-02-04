package com.main.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="TOPICS")
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_seq")
    @SequenceGenerator(name = "topic_seq", sequenceName = "topic_sequence", allocationSize = 1)
    protected Long topicId;

    protected String topicName;
    protected String topicDescription;
}
