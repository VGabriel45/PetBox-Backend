package springApplication.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springApplication.customers.Customer;
import springApplication.customers.CustomerService;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private QuestionConverter questionConverter;

    @GetMapping("/questions")
    @PreAuthorize("hasRole('ADMIN')")
    public List<QuestionDto> getAllQuestions(){
        List<Question> questions = questionService.findAll();
        return questionConverter.modelToDto(questions);
    }

    @GetMapping("/customers/{customerId}/questions")
    public List<QuestionDto> getAllQuestionsOfCustomer(@PathVariable UUID customerId){
        Customer customer = customerService.findById(customerId);
        List<Question> questions = questionService.findAllByCustomer(customer);
        return questionConverter.modelToDto(questions);
    }

    @GetMapping("/customers/{customerId}/questions/{questionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public QuestionDto getQuestionOfCustomer(@PathVariable UUID questionId){
        Question question = questionService.findById(questionId);
        return questionConverter.modelToDto(question);
    }

    @PostMapping("/customers/{customerId}/questions")
    @PreAuthorize("hasRole('USER')")
    public void addQuestion(@RequestBody Question question){
        questionService.saveQuestion(question);
    }

    @PutMapping("/customers/{customerId}/questions/{questionId}")
    @PreAuthorize("hasRole('USER')")
    public void updateQuestion(@PathVariable UUID questionId,@RequestBody Question question){
        Question updatedQuestion = questionService.findById(questionId);

        updatedQuestion.setDate(question.getDate());
        updatedQuestion.setSolved(question.isSolved());
        updatedQuestion.setText(question.getText());
        updatedQuestion.setSeen(question.isSeen());
        updatedQuestion.setResponse(question.getResponse());

        questionService.saveQuestion(updatedQuestion);
    }

    @GetMapping("/customers/{customerId}/questions/{questionId}/setSeen")
    public void markAsSeen(@PathVariable UUID questionId){
        Question updatedQuestion = questionService.findById(questionId);
        updatedQuestion.setSeen(true);
        questionService.saveQuestion(updatedQuestion);
    }

    @GetMapping("/customers/{customerId}/questions/{questionId}/setResponse")
    public void setResponse(@PathVariable UUID questionId, @RequestBody String response){
        Question updatedQuestion = questionService.findById(questionId);
        updatedQuestion.setResponse(response);
        questionService.saveQuestion(updatedQuestion);
    }

    @GetMapping("/customers/{customerId}/questions/{questionId}/setSolved")
    public void markAsSolved(@PathVariable UUID questionId){
        Question updatedQuestion = questionService.findById(questionId);
        updatedQuestion.setSolved(true);
        questionService.saveQuestion(updatedQuestion);
    }

    @GetMapping("/questions/seen")
    public int getNumberOfUnseenQuestions() {
        return questionService.getNumOfUnseenQuestions();
    }

    @DeleteMapping("/customers/{customerId}/questions/{questionId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public void deleteQuestion(@PathVariable UUID questionId){
        Question question = questionService.findById(questionId);
        questionService.deleteQuestion(question);
    }
}
