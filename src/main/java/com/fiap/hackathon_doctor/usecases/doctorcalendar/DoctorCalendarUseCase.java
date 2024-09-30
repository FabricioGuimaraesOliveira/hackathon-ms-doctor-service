package com.fiap.hackathon_doctor.usecases.doctorcalendar;

import com.fiap.hackathon_doctor.entity.doctorcalendar.gateway.DoctorCalendarGateway;
import com.fiap.hackathon_doctor.entity.doctorcalendar.model.DoctorCalendar;
import com.fiap.hackathon_doctor.infrastructure.doctor.exceptions.ResourceNotFoundException;
import java.util.List;

public class DoctorCalendarUseCase {

	final private DoctorCalendarGateway doctorCalendarGateway;

	public DoctorCalendarUseCase(DoctorCalendarGateway doctorCalendarGateway) {
		this.doctorCalendarGateway = doctorCalendarGateway;
	}

	public DoctorCalendar buscar(String id) {
		return doctorCalendarGateway.buscarCalendarPorId(id)
			.orElseThrow(() -> new ResourceNotFoundException("Calendar não encontrado para o id: " + id));
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

	public DoctorCalendar cadastrar(DoctorCalendar doctorCalendar) {
		return doctorCalendarGateway.salvar(doctorCalendar);
	}

	public DoctorCalendar atualizar(String id, DoctorCalendar request) {
		var doctorCalendar = doctorCalendarGateway.buscarCalendarPorId(id)
			.orElseThrow(() -> new ResourceNotFoundException("Calendar não encontrado para o id: " + id));
		doctorCalendar.atualizar(request.getStatus());
		return doctorCalendarGateway.salvar(doctorCalendar);
	}

}
