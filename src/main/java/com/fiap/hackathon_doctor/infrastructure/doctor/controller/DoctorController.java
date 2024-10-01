package com.fiap.hackathon_doctor.infrastructure.doctor.controller;

import com.fiap.hackathon_doctor.entity.doctor.model.Doctor;
import com.fiap.hackathon_doctor.infrastructure.doctor.dto.request.DoctorRequestDTO;
import com.fiap.hackathon_doctor.infrastructure.doctor.dto.response.DoctorResponseDTO;
import com.fiap.hackathon_doctor.usecases.doctor.DoctorUseCase;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.UUID;

@RestController
@RequestMapping("/doctors")
@Tag(name = "doctor", description = "API responsável pelo cadastro de médicos")
public class DoctorController {

	private final DoctorUseCase doctorUseCase;
	private final ModelMapper modelMapper;

	public DoctorController(DoctorUseCase doctorUseCase, ModelMapper modelMapper) {
		this.doctorUseCase = doctorUseCase;
		this.modelMapper = modelMapper;
	}

	@Operation(summary = "Buscar um Médico por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso ao buscar o Médico",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = DoctorResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Erro ao buscar o Médico", content = @Content),
			@ApiResponse(responseCode = "404", description = "Médico não encontrado", content = @Content) })
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public DoctorResponseDTO buscarDoctorById(
			@Schema(description = "Id do Médico") @PathVariable(value = "id") UUID id) {
		// Chama o use case para buscar o médico pelo ID
		Doctor doctor = doctorUseCase.buscarPorId(id);

		// Retorna o médico encontrado, mapeado para o DTO de resposta
		return modelMapper.map(doctor, DoctorResponseDTO.class);
	}

	@Operation(summary = "Cadastrar um Médico")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Sucesso ao cadastrar o Médico",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = DoctorResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Erro ao cadastrar o Médico", content = @Content) })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
		public DoctorResponseDTO cadastrarDoctor(@RequestBody @Valid DoctorRequestDTO doctorRequestDTO) {
		Doctor doctor = new Doctor(
				UUID.randomUUID(),  // Gerando o UUID para o médico
				doctorRequestDTO.getNome(),
				doctorRequestDTO.getCpf(),
				doctorRequestDTO.getNumeroCrm(),
				doctorRequestDTO.getEmail(),
				doctorRequestDTO.getSenha(),
				null
		);

			Doctor savedDoctor = doctorUseCase.cadastrar(doctor);
			return mapToResponseDTO(doctor);
		}

	private DoctorResponseDTO mapToResponseDTO(Doctor doctor) {
		return new DoctorResponseDTO(doctor.getId(), doctor.getNome(), doctor.getEspecialidade());

	}
}
