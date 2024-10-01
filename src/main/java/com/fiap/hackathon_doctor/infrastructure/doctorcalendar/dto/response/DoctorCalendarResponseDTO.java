package com.fiap.hackathon_doctor.infrastructure.doctorcalendar.dto.response;

import com.fiap.hackathon_doctor.entity.doctorcalendar.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorCalendarResponseDTO {

	private UUID id;
	private UUID doctorId;
	private String dataAgenda;
	private String horaAgenda;
	private Status status;
}
