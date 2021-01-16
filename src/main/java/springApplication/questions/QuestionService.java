package springApplication.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springApplication.customers.Customer;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;


    public List<Question> findAll() {
        return questionRepository.findQuestionsByResponseIsNull();
    }

    public List<Question> findAllByCustomer(Customer customer) {
        return questionRepository.findAllByCustomer(customer);
    }

    public Question findById(UUID questionId) {
        return questionRepository.findById(questionId);
    }

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    public int getNumOfUnseenQuestions() {
        return questionRepository.getNumOfUnseenQuestions();
    }

    public void deleteQuestion(Question question) {
        questionRepository.delete(question);
    }
}
