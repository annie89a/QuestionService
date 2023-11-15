package com.practise.questionservice.controller;


import com.practise.questionservice.model.Question;
import com.practise.questionservice.model.QuestionWrapper;
import com.practise.questionservice.model.Response;
import com.practise.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //accept request
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public List<Question> getAllQuestion() //we want to return object
    {
        return questionService.getAllQuestions();

        //return a list of questions. - 1 data.
       // how to return data and status code., so we use a response entity
    }

    @GetMapping("allQuestionsRE")
    public ResponseEntity<List<Question>> getAllQuestionRE() //we want to return object
    {
        return  questionService.getAllQuestionsRE();

        //return a list of questions. - 1 data.
        // how to return data and status code., so we use a response entity
    }

    @GetMapping("category/{category}") //{placeholder}
    public List<Question> getQuestionsByCategory(@PathVariable String category) //if you are using //{placeholder}
    {
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("categoryRE/{category}") //{placeholder}
    public ResponseEntity<List<Question>> getQuestionsByCategoryRE(@PathVariable String category) //if you are using //{placeholder}
    {
        return questionService.getQuestionsByCategoryRE(category);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody  Question question) //@RequestBody :  sending data , bcz we are sending data in a  request from the client side to the  body
    { //i.e in the body, we will be haivng the data

        return questionService.addQuestion(question);
    }

    @PostMapping("addRE")
    public ResponseEntity<String> addQuestionRE(@RequestBody Question question) //@RequestBody :  sending data , bcz we are sending data in a  request from the client side to the  body
    { //i.e in the body, we will be having the data

        return questionService.addQuestionRE(question);
    }

    @DeleteMapping("deleteById/{id}")
    public String deleteQuestion (@PathVariable Integer id)
    {
        return questionService.deleleteById(id);
    }

    @PutMapping("updateById/{id}")
    public String updateQuestion(@PathVariable Integer id, @RequestBody Question question)
    {
        return questionService.updateById(id, question);
    }

    @GetMapping("generate")
    public ResponseEntity  <List<Integer>> getQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam Integer numQuestions){ //we return a list of the ids of the questions
        return questionService.getQuestionsForQuiz(categoryName,numQuestions );
    }

    //based on the given ids we want to get its questions
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds)
    {
        //we are using just questions without the answer, and that is why we are using a questionWrapper
        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionService.getScore(responses);
    }

    //tell quiz service to generate quiz
    // get questions by questionid
    //getScore


}
