package springApplication.appointments;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentConverter {

    @Autowired
    private ModelMapper modelMapper;

    public AppointmentDto modelToDto(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDto.class);
    }

    public List<AppointmentDto> modelToDto(List<Appointment> appointments) {
        return appointments.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    public Appointment dtoToModel(AppointmentDto appointmentDto) {
        return modelMapper.map(appointmentDto, Appointment.class);
    }

    public List<Appointment> dtoToModel(List<AppointmentDto> appointmentDtos) {
        return appointmentDtos.stream().map(this::dtoToModel).collect(Collectors.toList());
    }

}
