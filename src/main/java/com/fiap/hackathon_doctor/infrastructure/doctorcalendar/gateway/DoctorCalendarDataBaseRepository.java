package com.fiap.hackathon_doctor.infrastructure.doctorcalendar.gateway;

import com.fiap.hackathon_doctor.entity.doctorcalendar.gateway.DoctorCalendarGateway;
import com.fiap.hackathon_doctor.entity.doctorcalendar.model.DoctorCalendar;
import com.fiap.hackathon_doctor.entity.doctorcalendar.model.Status;
import com.fiap.hackathon_doctor.infrastructure.persistence.doctor.DoctorEntity;
import com.fiap.hackathon_doctor.infrastructure.persistence.doctorcalendar.DoctorCalendarEntity;
import com.fiap.hackathon_doctor.infrastructure.persistence.doctorcalendar.DoctorCalendarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class DoctorCalendarDataBaseRepository implements DoctorCalendarGateway {

	private final DoctorCalendarRepository doctorCalendarRepository;

	private final ModelMapper modelMapper;

	public DoctorCalendarDataBaseRepository(DoctorCalendarRepository doctorCalendarRepository,
			ModelMapper modelMapper) {
		this.doctorCalendarRepository = doctorCalendarRepository;
		this.modelMapper = modelMapper;
	}

	public static DoctorCalendar toDomain(DoctorCalendarEntity entity) {
		DoctorCalendar doctorCalendar = new DoctorCalendar();
		doctorCalendar.setId(entity.getId());
		doctorCalendar.setDoctorId(entity.getDoctorId());
		doctorCalendar.setDataAgenda(entity.getDataAgenda());
		doctorCalendar.setHoraAgenda(entity.getHoraAgenda());
		doctorCalendar.setStatus(entity.getStatus());
		return doctorCalendar;
	}
	@Override
	public DoctorCalendar salvar(DoctorCalendar doctorCalendar) {
		var doctorCalendarEntity = doctorCalendarRepository
			.saveAndFlush(modelMapper.map(doctorCalendar, DoctorCalendarEntity.class));
		return toDomain(doctorCalendarEntity);
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

		return 	Optional.of(toDomain(doctorCalendar.get()));

	}

	@Override
	public Page<DoctorCalendar> listarPaginado(Pageable pageable) {
		Page<DoctorCalendarEntity> doctorCalendarEntitiesPage = doctorCalendarRepository.findAll(pageable);
		return doctorCalendarEntitiesPage
			.map(doctorEntity -> modelMapper.map(doctorCalendarEntitiesPage, DoctorCalendar.class));
	}
	@Override
	public List<DoctorCalendar> buscarTodos() {
		var doctorCalendarEntities = doctorCalendarRepository.findByStatus(Status.DISPONIVEL);
		return doctorCalendarEntities.stream()
				.map(entity -> modelMapper.map(entity, DoctorCalendar.class))
				.collect(Collectors.toList());
	}
}
