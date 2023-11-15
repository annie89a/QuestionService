package com.practise.questionservice.repositories;


import com.practise.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> { //all fecthing data from the db will be handled by data jpa
    List<Question> findByCategory(String category); //find data by category.

    @Query(value = "SELECT q.id FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true) //we can mention our query and whether it is native. so we write JPQL query
    List<Integer> findRandomQuestionsByCategory(String category, Integer numQ); //we are returning question's ID so that will be integer
    //if you need other data, we need HQL or JPQL
    //jpa is smart to give you data because the column is present in the table
}

