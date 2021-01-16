package springApplication.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions")
    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    @GetMapping("/customers/{customerId}/questions")
    public List<Question> getAllQuestionsOfCustomer(@PathVariable UUID customerId){
        return questionRepository.findAllByCustomerId(customerId);
    }

    @GetMapping("/customers/{customerId}/questions/{questionId}")
    public Question getQuestionOfCustomer(@PathVariable UUID questionId){
        return questionRepository.findById(questionId);
    }

    @PostMapping("/customers/{customerId}/questions")
    public void addQuestion(@RequestBody Question question){
        questionRepository.save(question);
    }

    @PutMapping("/customers/{customerId}/questions/{questionId}")
    public void updateQuestion(@PathVariable UUID questionId,@RequestBody Question question){
        Question updatedQuestion = questionRepository.findById(questionId);
        updatedQuestion.setDate(question.getDate());
        updatedQuestion.setSolved(question.isSolved());
        updatedQuestion.setText(question.getText());
        updatedQuestion.setSeen(question.isSeen());
        updatedQuestion.setResponse(question.getResponse());

        questionRepository.save(updatedQuestion);
    }

    @GetMapping("/customers/{customerId}/questions/{questionId}/setSeen")
    public void markAsSeen(@PathVariable UUID questionId){
        Question updatedQuestion = questionRepository.findById(questionId);
        System.out.println(updatedQuestion);
        updatedQuestion.setSeen(true);
        questionRepository.save(updatedQuestion);
    }

    @GetMapping("/customers/{customerId}/questions/{questionId}/setResponse")
    public void setResponse(@PathVariable UUID questionId, @RequestBody String response){
        Question updatedQuestion = questionRepository.findById(questionId);
        updatedQuestion.setResponse(response);
        questionRepository.save(updatedQuestion);
    }

    @GetMapping("/customers/{customerId}/questions/{questionId}/setSolved")
    public void markAsSolved(@PathVariable UUID questionId){
        Question updatedQuestion = questionRepository.findById(questionId);
//        System.out.println(updatedQuestion);
        updatedQuestion.setSolved(true);
        questionRepository.save(updatedQuestion);
    }

    @GetMapping("/questions/seen")
    public int getNumberOfUnseenQuestions() {
        return questionRepository.getNumOfUnseenQuestions();
    }

    @DeleteMapping("/customers/{customerId}/questions/{questionId}")
    public void deleteQuestion(@PathVariable UUID questionId){
        Question question = questionRepository.findById(questionId);
        questionRepository.delete(question);
    }
}
