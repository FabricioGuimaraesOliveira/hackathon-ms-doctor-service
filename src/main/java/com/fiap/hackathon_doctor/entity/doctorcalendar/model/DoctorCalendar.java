package com.fiap.hackathon_doctor.entity.doctorcalendar.model;

import java.util.UUID;

public class DoctorCalendar {

	private UUID id;

	private UUID doctorId;

	private String dataAgenda;

	private String horaAgenda;

	private Status status;

	public DoctorCalendar(UUID id, UUID doctorId, String dataAgenda, String horaAgenda, Status status) {
		this.id = id;
		this.doctorId = doctorId;
		this.dataAgenda = dataAgenda;
		this.horaAgenda = horaAgenda;
		this.status = status;
	}

	public UUID getId() {
		return id;
	}

	public UUID getDoctorId() {
		return doctorId;
	}

	public String getDataAgenda() {
		return dataAgenda;
	}

	public String getHoraAgenda() {
		return horaAgenda;
	}

	public Status getStatus() {
		return status;
	}

	public void validarCadastro() {

		if (this.doctorId == null) {
			throw new IllegalArgumentException("DoctorId não pode ser nulo");
		}
		if (this.dataAgenda == null || this.dataAgenda.isEmpty()) {
			throw new IllegalArgumentException("Data não pode ser nula ou vazia");
		}
		if (this.horaAgenda == null || this.horaAgenda.isEmpty()) {
			throw new IllegalArgumentException("Hora não pode ser nula ou vazia");
		}
		if (this.status == null || this.status.toString().isEmpty()) {
			throw new IllegalArgumentException("Status não pode ser nulo");
		}
	}

	public void atualizar(Status status) {
		if (status == null) {
			throw new IllegalArgumentException("Status não pode ser nulo");
		}
		this.status = status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setDataAgenda(String dataAgenda) {
		this.dataAgenda = dataAgenda;
	}

	public void setHoraAgenda(String horaAgenda) {
		this.horaAgenda = horaAgenda;
	}

	public void setDoctorId(UUID doctorId) {
		this.doctorId = doctorId;
	}

}
