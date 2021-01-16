package springApplication.questions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springApplication.customers.Customer;
import springApplication.pets.Pet;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, String> {
    Question findById(UUID personId);

    void deleteById(UUID questionId);

    List<Question> findAllByCustomerId(UUID personId);

    @Query(
            value = "SELECT COUNT(q) FROM Question q WHERE q.seen = false")
    int getNumOfUnseenQuestions();
}
