package springApplication.pets;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springApplication.employees.Employee;
import springApplication.employees.EmployeeDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetConverter {

    @Autowired
    private ModelMapper modelMapper;

    public PetDto modelToDto(Pet pet) {
        return modelMapper.map(pet, PetDto.class);
    }

    public List<PetDto> modelToDto(List<Pet> pets) {
        return pets.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    public Pet dtoToModel(PetDto petDto) {
        return modelMapper.map(petDto, Pet.class);
    }

    public List<Pet> dtoToModel(List<PetDto> petDtos) {
        return petDtos.stream().map(this::dtoToModel).collect(Collectors.toList());
    }
}
