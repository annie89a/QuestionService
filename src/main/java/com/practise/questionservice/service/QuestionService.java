package com.practise.questionservice.service;


import com.practise.questionservice.model.Question;
import com.practise.questionservice.model.QuestionWrapper;
import com.practise.questionservice.model.Response;
import com.practise.questionservice.repositories.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public List<Question> getAllQuestions() {

        return questionDao.findAll();
    }

    public ResponseEntity<List<Question>> getAllQuestionsRE() {
        try
        {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    
    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategoryRE(String category) {
        try
        {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public String addQuestion(Question question) {
        questionDao.save(question);
        return "OK";
    }

    public ResponseEntity<String> addQuestionRE(Question question) {
        try
        {
            questionDao.save(question);
            return new ResponseEntity<>("add OK", HttpStatus.CREATED);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return  new ResponseEntity<>("add NOK", HttpStatus.BAD_REQUEST);
    }

    public String deleleteById(Integer id) {
        questionDao.deleteById(id);
        return "Delete OK";
    }

    @Transactional
    public String updateById(Integer id, Question question) {
        // First, check if the question with the given id exists in the database
        Optional<Question> existingQuestion = questionDao.findById(id);

        if (existingQuestion.isPresent()) {
            Question questionToUpdate = existingQuestion.get();

            // Update the fields of the existing question with the values from updatedQuestion
            questionToUpdate.setCategory(question.getCategory());
            questionToUpdate.setQuestionTitle(question.getQuestionTitle());
            questionToUpdate.setOption1(question.getOption1());
            questionToUpdate.setOption2(question.getOption2());
            questionToUpdate.setOption3(question.getOption3());
            questionToUpdate.setOption4(question.getOption4());
            questionToUpdate.setRightAnswer(question.getRightAnswer());
            questionToUpdate.setDifficultylevel(question.getDifficultylevel());

            // Save the updated question back to the database
            questionDao.save(questionToUpdate);
            return "Update OK";
        } else {
            return "Question not found"; // You can return an error message if the question with the given id doesn't exist.
        }
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName, numQuestions);

        return new ResponseEntity<>(questions, HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for (Integer id : questionIds)
        {
            questions.add(questionDao.findById(id).get());
        }

        for (Question question: questions)
        {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
       // Quiz quiz = quizDao.findById(id).get();
      //  List<Question> questions = quiz.getQuestions();
        int right =0;
        //int i=0;
        //int result =0;
        for (Response response: responses)
        {
            Question question = questionDao.findById(response.getId()).get();//findbyid is optional, so we use get
            if (response.getResponse().equals(question.getRightAnswer()))
            {
                right++;
            }
           // i++;
        }
        //  result=(int )(((double)(right/i)) * 100);
        // (int) (((double) right / i) * 100);
        return new ResponseEntity<>(right, HttpStatus.OK);
    }


    //to make it more complete , we need to add exception, if something goes wrong

    // we also have to specify the status codes, for that we need to use response entity.
    //and then we have to use the quiz service
    //we want to work with HTTP response status codes.

    // when website is not available or when we enter a wrong link, we get erro

//    /wwhat are these status code, to whom this is helpful.Question
//    //there are multiple status code , which is not need to rbe remembered/
//    //but its for the developer. based on the website received.
//    client side will generate some message
//            when I request for resource.
//            status 00, all ok.
//
//    But what if its a n error, 492. maybe can rpint file not found.Question
//    multiple status codes

    

}
