package com.fiap.hackathon_doctor.infrastructure.persistence.doctor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Entity
@Data
@Table(name = "doctor")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DoctorEntity {

	@Id
	private UUID id;

	@Column(nullable = false)
	@Size(max = 11, min = 11)
	@CPF
	private String cpf;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String email;

	@Column(name = "numero_crm", nullable = false)
	private String numeroCrm;

	@Column(nullable = false)
	private String senha;

	@Column(nullable = false)
	private String especialidade;

}
