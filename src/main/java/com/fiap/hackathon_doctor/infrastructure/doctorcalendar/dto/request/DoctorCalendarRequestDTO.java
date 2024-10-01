package com.fiap.hackathon_doctor.infrastructure.doctorcalendar.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DoctorCalendarRequestDTO {

	private UUID doctorId;
	private String dataAgenda;
	private String horaAgenda;
}

