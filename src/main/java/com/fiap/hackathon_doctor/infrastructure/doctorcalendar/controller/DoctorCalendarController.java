package com.fiap.hackathon_doctor.infrastructure.doctorcalendar.controller;

import com.fiap.hackathon_doctor.usecases.doctorcalendar.DoctorCalendarUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor-calendars/")
@Tag(name = "doctor-calendars", description = "API responsável pelo cadastrado de horário dos médicos")
public class DoctorCalendarController {

	private final DoctorCalendarUseCase doctorCalendarUseCase;

	private final ModelMapper modelMapper;

	public DoctorCalendarController(DoctorCalendarUseCase doctorCalendarUseCase, ModelMapper modelMapper) {
		this.doctorCalendarUseCase = doctorCalendarUseCase;
		this.modelMapper = modelMapper;
	}

}
