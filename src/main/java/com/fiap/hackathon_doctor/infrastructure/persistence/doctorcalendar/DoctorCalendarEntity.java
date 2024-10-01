package com.fiap.hackathon_doctor.infrastructure.persistence.doctorcalendar;

import com.fiap.hackathon_doctor.entity.doctorcalendar.model.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Entity
@Data
@Table(name = "calendar")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DoctorCalendarEntity {

	@Id
	private UUID id;

	@Column(nullable = false)
	private UUID doctorId;

	@Column(nullable = false)
	private String dataAgenda;

	@Column(nullable = false)
	private String horaAgenda;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;

}
