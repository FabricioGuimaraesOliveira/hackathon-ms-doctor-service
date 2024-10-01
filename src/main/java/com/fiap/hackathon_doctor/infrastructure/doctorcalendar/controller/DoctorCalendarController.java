package com.fiap.hackathon_doctor.infrastructure.doctorcalendar.controller;

import com.fiap.hackathon_doctor.infrastructure.doctorcalendar.dto.request.DoctorCalendarRequestDTO;
import com.fiap.hackathon_doctor.infrastructure.doctorcalendar.dto.response.DoctorCalendarResponseDTO;
import com.fiap.hackathon_doctor.usecases.doctorcalendar.DoctorCalendarUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

@RestController
@RequestMapping("/doctor-calendars")
@Tag(name = "doctor-calendars", description = "API responsável pelo cadastro de horário dos médicos")
public class DoctorCalendarController {

	private final DoctorCalendarUseCase doctorCalendarUseCase;
	private final ModelMapper modelMapper;

	public DoctorCalendarController(DoctorCalendarUseCase doctorCalendarUseCase, ModelMapper modelMapper) {
		this.doctorCalendarUseCase = doctorCalendarUseCase;
		this.modelMapper = modelMapper;
	}

	@Operation(summary = "Cadastrar horário do médico")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Sucesso ao cadastrar o horário",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = DoctorCalendarResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Erro ao cadastrar o horário", content = @Content)
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DoctorCalendarResponseDTO cadastrarHorario(
			@RequestBody @Valid DoctorCalendarRequestDTO doctorCalendarRequestDTO) {
		var savedDoctorCalendar = doctorCalendarUseCase.cadastrar(doctorCalendarRequestDTO);
		return  modelMapper.map(savedDoctorCalendar, DoctorCalendarResponseDTO.class);

	}

	@Operation(summary = "Agendar horário do médico")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Horário agendado com sucesso",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = DoctorCalendarResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Erro ao agendar o horário", content = @Content),
			@ApiResponse(responseCode = "404", description = "Horário não encontrado", content = @Content)
	})
	@PatchMapping("/doctor/schedule")
	@ResponseStatus(HttpStatus.OK)
	public DoctorCalendarResponseDTO agendarHorario(
													@RequestBody @Valid DoctorCalendarRequestDTO doctorCalendarRequestDTO) {
		// Buscar o horário a ser agendado pelo doctorId e os parâmetros de data e hora
		var scheduledDoctorCalendar = doctorCalendarUseCase.agendarHorario(doctorCalendarRequestDTO.getDoctorId(),
				doctorCalendarRequestDTO.getDataAgenda(),
				doctorCalendarRequestDTO.getHoraAgenda());

		return modelMapper.map(scheduledDoctorCalendar, DoctorCalendarResponseDTO.class);
	}


	@Operation(summary = "Listar horários disponíveis do médico")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso ao listar os horários disponíveis",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = DoctorCalendarResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Médico não encontrado", content = @Content)
	})
	@GetMapping("/doctor/available-times")
	@ResponseStatus(HttpStatus.OK)
	public List<DoctorCalendarResponseDTO> listarHorariosDisponiveis() {
		var availableTimes = doctorCalendarUseCase.buscarTodos();

		return availableTimes.stream()
				.map(availableTime -> modelMapper.map(availableTime, DoctorCalendarResponseDTO.class))
				.collect(Collectors.toList());
	}

}
