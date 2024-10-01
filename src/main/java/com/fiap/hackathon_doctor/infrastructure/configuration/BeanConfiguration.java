package com.fiap.hackathon_doctor.infrastructure.configuration;

import com.fiap.hackathon_doctor.entity.doctor.gateway.DoctorCpfGateway;
import com.fiap.hackathon_doctor.entity.doctor.gateway.DoctorEmailGateway;
import com.fiap.hackathon_doctor.entity.doctor.gateway.DoctorGateway;
import com.fiap.hackathon_doctor.entity.doctorcalendar.gateway.DoctorCalendarGateway;
import com.fiap.hackathon_doctor.infrastructure.doctor.gateway.DoctorDataBaseRepository;
import com.fiap.hackathon_doctor.infrastructure.doctorcalendar.gateway.DoctorCalendarDataBaseRepository;
import com.fiap.hackathon_doctor.infrastructure.persistence.doctor.DoctorRepository;
import com.fiap.hackathon_doctor.infrastructure.persistence.doctorcalendar.DoctorCalendarRepository;
import com.fiap.hackathon_doctor.usecases.doctor.DoctorUseCase;
import com.fiap.hackathon_doctor.usecases.doctorcalendar.DoctorCalendarUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public DoctorGateway createDoctorGateway(DoctorRepository springDoctorRepository, ModelMapper mapper) {
		return new DoctorDataBaseRepository(springDoctorRepository, mapper);
	}

	@Bean
	DoctorUseCase createDoctorUseCase(DoctorGateway doctorGateway) {
		return new DoctorUseCase(doctorGateway);
	}

	@Bean
	public DoctorCalendarGateway createDoctorCalendarGateway(DoctorCalendarRepository springDoctorCalendarRepository,
			ModelMapper mapper) {
		return new DoctorCalendarDataBaseRepository(springDoctorCalendarRepository, mapper);
	}

	@Bean
	DoctorCalendarUseCase createDoctorCalendarUseCase(DoctorCalendarGateway doctorCalendarGateway, ModelMapper mapper) {
		return new DoctorCalendarUseCase(doctorCalendarGateway,mapper);
	}

}
