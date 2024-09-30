package com.fiap.hackathon_doctor.infrastructure.doctor.dto.response;

import com.fiap.hackathon_doctor.infrastructure.doctorcalendar.dto.response.DoctorCalendarResponseDTO;

import java.util.List;
import java.util.UUID;

public class DoctorResponseDTO {

	private UUID id;

	private String nome;

	private String especialidade;

	private List<DoctorCalendarResponseDTO> horariosDisponiveis;

	public DoctorResponseDTO(UUID id, String nome, String especialidade,
			List<DoctorCalendarResponseDTO> horariosDisponiveis) {
		this.id = id;
		this.nome = nome;
		this.especialidade = especialidade;
		this.horariosDisponiveis = horariosDisponiveis;
	}

	public DoctorResponseDTO(UUID id, String nome, String especialidade) {
		this.id = id;
		this.nome = nome;
		this.especialidade = especialidade;
	}

	public UUID getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEspecialidade() {
		return especialidade;
	}

}
