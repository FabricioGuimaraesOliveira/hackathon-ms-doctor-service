package com.fiap.hackathon_doctor.infrastructure.doctor.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

@Data
@EqualsAndHashCode
@ToString
public class DoctorRequestDTO {

	@Schema(description = "Nome do Médico", example = "Médico da Silva")
	@NotNull(message = "O nome do Médico não pode ser nullo")
	private String nome;

	@Schema(description = "Email do Médico", example = "exemplo@exemplo.com")
	@NotNull(message = "O email do Médico não pode ser nullo")
	private String email;

	@Schema(description = "Número do CRM do Médico", example = "123456")
	private String numeroCrm;

	@Schema(description = "Senha do Médico", example = "123456")
	private String senha;

	@Schema(description = "CPF do Médico", example = "34511780013")
	@NotNull(message = "CPF do Médico não pode ser nullo")
	@CPF(message = "CPF Invalido")
	private String cpf;

}
