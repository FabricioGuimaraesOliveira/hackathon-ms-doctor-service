package com.fiap.hackathon_doctor.infrastructure.doctorcalendar.dto.response;

import com.fiap.hackathon_doctor.entity.doctorcalendar.model.Status;

import java.util.UUID;

public class DoctorCalendarResponseDTO {

	private UUID id;

	private UUID doctorId;

	private String dataAgenda;

	private String horaAgenda;

	private Status status;

	public DoctorCalendarResponseDTO(UUID id, UUID doctorId, String dataAgenda, String horaAgenda, Status status) {
		this.id = id;
		this.doctorId = doctorId;
		this.dataAgenda = dataAgenda;
		this.horaAgenda = horaAgenda;
		this.status = status;
	}

}
