package com.fiap.hackathon_doctor.infrastructure.doctorcalendar.gateway;

import com.fiap.hackathon_doctor.entity.doctorcalendar.gateway.DoctorCalendarGateway;
import com.fiap.hackathon_doctor.entity.doctorcalendar.model.DoctorCalendar;
import com.fiap.hackathon_doctor.infrastructure.persistence.doctor.DoctorEntity;
import com.fiap.hackathon_doctor.infrastructure.persistence.doctorcalendar.DoctorCalendarEntity;
import com.fiap.hackathon_doctor.infrastructure.persistence.doctorcalendar.DoctorCalendarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DoctorCalendarDataBaseRepository implements DoctorCalendarGateway {

	private final DoctorCalendarRepository doctorCalendarRepository;

	private final ModelMapper modelMapper;

	public DoctorCalendarDataBaseRepository(DoctorCalendarRepository doctorCalendarRepository,
			ModelMapper modelMapper) {
		this.doctorCalendarRepository = doctorCalendarRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public DoctorCalendar salvar(DoctorCalendar doctorCalendar) {
		var doctorCalendarEntity = doctorCalendarRepository
			.saveAndFlush(modelMapper.map(doctorCalendar, DoctorCalendarEntity.class));
		return modelMapper.map(doctorCalendarEntity, DoctorCalendar.class);
	}

	@Override
	public Optional<DoctorCalendar> buscarCalendarPorId(String id) {
		var doctorCalendarEntity = doctorCalendarRepository.findById(UUID.fromString(id));
		return doctorCalendarEntity.map(entity -> modelMapper.map(entity, DoctorCalendar.class));
	}

	@Override
	public Optional<List<DoctorCalendar>> buscarCalendarPorDoctorId(String doctorId) {
		var doctorCalendarEntities = doctorCalendarRepository
			.findDoctorCalendarEntitiesByDoctorId(UUID.fromString(doctorId));

		return doctorCalendarEntities.filter(entities -> !entities.isEmpty())
			.map(entities -> entities.stream().map(entity -> modelMapper.map(entity, DoctorCalendar.class)).toList());

	}

	@Override
	public Optional<DoctorCalendar> buscarCalendarPorDoctorIdDataAgenda(String doctorId, String dataAgenda,
			String horaAgenda) {
		var doctorCalendar = doctorCalendarRepository.findDoctorCalendarEntityByDoctorIdAndDataAgendaAndHoraAgenda(
				UUID.fromString(doctorId), dataAgenda, horaAgenda);

		return doctorCalendar.map(entity -> modelMapper.map(entity, DoctorCalendar.class));

	}

	@Override
	public Page<DoctorCalendar> listarPaginado(Pageable pageable) {
		Page<DoctorCalendarEntity> doctorCalendarEntitiesPage = doctorCalendarRepository.findAll(pageable);
		return doctorCalendarEntitiesPage
			.map(doctorEntity -> modelMapper.map(doctorCalendarEntitiesPage, DoctorCalendar.class));
	}

}
