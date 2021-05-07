package springApplication.questions;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springApplication.pets.Pet;
import springApplication.pets.PetDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionConverter {
    @Autowired
    private ModelMapper modelMapper;

    public QuestionDto modelToDto(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }

    public List<QuestionDto> modelToDto(List<Question> questions) {
        return questions.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    public Question dtoToModel(QuestionDto questionDto) {
        return modelMapper.map(questionDto, Question.class);
    }

    public List<Question> dtoToModel(List<QuestionDto> questionDtos) {
        return questionDtos.stream().map(this::dtoToModel).collect(Collectors.toList());
    }
}
