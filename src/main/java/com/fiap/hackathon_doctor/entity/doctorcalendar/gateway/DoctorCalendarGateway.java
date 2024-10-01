package com.fiap.hackathon_doctor.entity.doctorcalendar.gateway;

import com.fiap.hackathon_doctor.entity.doctorcalendar.model.DoctorCalendar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DoctorCalendarGateway {

	DoctorCalendar salvar(DoctorCalendar calendar);

	Optional<DoctorCalendar> buscarCalendarPorId(String id);

	Optional<List<DoctorCalendar>> buscarCalendarPorDoctorId(String doctorId);

	Optional<DoctorCalendar> buscarCalendarPorDoctorIdDataAgenda(String doctorId, String dataAgenda, String horaAgenda);

	Page<DoctorCalendar> listarPaginado(Pageable pageable);

	List<DoctorCalendar> buscarTodos();
}
