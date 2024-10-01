package com.fiap.hackathon_doctor.usecases.doctorcalendar;

import com.fiap.hackathon_doctor.entity.doctorcalendar.gateway.DoctorCalendarGateway;
import com.fiap.hackathon_doctor.entity.doctorcalendar.model.DoctorCalendar;
import com.fiap.hackathon_doctor.entity.doctorcalendar.model.Status;
import com.fiap.hackathon_doctor.infrastructure.doctor.exceptions.ResourceNotFoundException;
import com.fiap.hackathon_doctor.infrastructure.doctorcalendar.dto.request.DoctorCalendarRequestDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;

public class DoctorCalendarUseCase {

	final private DoctorCalendarGateway doctorCalendarGateway;
	private final ModelMapper modelMapper;


	public DoctorCalendarUseCase(DoctorCalendarGateway doctorCalendarGateway, ModelMapper modelMapper) {
		this.doctorCalendarGateway = doctorCalendarGateway;
        this.modelMapper = modelMapper;
    }

	public DoctorCalendar buscar(String id) {
		return doctorCalendarGateway.buscarCalendarPorId(id)
			.orElseThrow(() -> new ResourceNotFoundException("Calendar não encontrado para o id: " + id));
	}
	public List<DoctorCalendar> buscarTodos() {
		return doctorCalendarGateway.buscarTodos();  // Agora retorna uma lista simples
	}

	public DoctorCalendar agendarHorario(UUID doctorId, String dataAgenda, String horaAgenda) {
		// Buscar o horário com base no doctorId, data e hora
		var doctorCalendar = doctorCalendarGateway.buscarCalendarPorDoctorIdDataAgenda(doctorId.toString(), dataAgenda, horaAgenda)
				.orElseThrow(() -> new ResourceNotFoundException("Horário não encontrado para o médico: " + doctorId));
		if (doctorCalendar.getStatus() == Status.AGENDADO) {
			throw new IllegalStateException("Horário já está agendado e não está mais disponível");
		}
		// Atualizar o status para AGENDADO
		doctorCalendar.atualizar(Status.AGENDADO);

		// Salvar as mudanças
		return doctorCalendarGateway.salvar(doctorCalendar);
	}

	public List<DoctorCalendar> buscarPorDoctorId(String doctorId) {
		return doctorCalendarGateway.buscarCalendarPorDoctorId(doctorId)
			.orElseThrow(() -> new ResourceNotFoundException("Calendar não encontrado para o doctorId: " + doctorId));
	}

	public DoctorCalendar buscarPorDoctorIdAndDataAgendaAndHoraAgenda(String doctorId, String dataAgenda,
			String horaAgenda) {
		return doctorCalendarGateway.buscarCalendarPorDoctorIdDataAgenda(doctorId, dataAgenda, horaAgenda)
			.orElseThrow(() -> new ResourceNotFoundException("Calendar não encontrado para o doctorId: " + doctorId));
	}

	public DoctorCalendar cadastrar(DoctorCalendarRequestDTO doctorCalendarRequestDTO) {
		DoctorCalendar doctorCalendar = new DoctorCalendar();

		// Seta os valores do DTO no objeto DoctorCalendar
		doctorCalendar.setDoctorId(doctorCalendarRequestDTO.getDoctorId());
		doctorCalendar.setDataAgenda(doctorCalendarRequestDTO.getDataAgenda());
		doctorCalendar.setHoraAgenda(doctorCalendarRequestDTO.getHoraAgenda());

		// Definindo o status como DISPONIVEL
		doctorCalendar.setStatus(Status.DISPONIVEL);
		// Gera um UUID para o ID, caso ainda não tenha sido gerado
		doctorCalendar.setId(UUID.randomUUID());
		return doctorCalendarGateway.salvar(doctorCalendar);
	}

	public DoctorCalendar atualizar(String id, DoctorCalendar request) {
		var doctorCalendar = doctorCalendarGateway.buscarCalendarPorId(id)
			.orElseThrow(() -> new ResourceNotFoundException("Calendar não encontrado para o id: " + id));
		doctorCalendar.atualizar(request.getStatus());
		return doctorCalendarGateway.salvar(doctorCalendar);
	}

}
