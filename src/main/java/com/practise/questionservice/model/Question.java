package com.practise.questionservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;

@Data // so with this, getters and setters are not needed
@Entity //we want to map our column with our class
public class Question {
    //id, category, difficultylevel, option1, option2, option3, option4, question_title, right_answer)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String category;
    private String questionTitle; //the ORM framework, JPA will take care of the casing
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultylevel;

    // Default constructor
    public Question() {
        // Empty constructor is needed by Hibernate
    }

}
