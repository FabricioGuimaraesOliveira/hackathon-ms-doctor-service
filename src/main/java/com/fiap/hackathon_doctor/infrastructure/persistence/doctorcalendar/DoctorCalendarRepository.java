package com.fiap.hackathon_doctor.infrastructure.persistence.doctorcalendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fiap.hackathon_doctor.entity.doctorcalendar.model.Status;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DoctorCalendarRepository
		extends JpaRepository<DoctorCalendarEntity, String>, JpaSpecificationExecutor<DoctorCalendarEntity> {

	Optional<DoctorCalendarEntity> findById(UUID s);

	Optional<List<DoctorCalendarEntity>> findDoctorCalendarEntitiesByDoctorId(UUID doctorId);

	Optional<DoctorCalendarEntity> findDoctorCalendarEntityByDoctorIdAndDataAgendaAndHoraAgenda(UUID doctorId,
			String dataAgenda, String horaAgenda);
	List<DoctorCalendarEntity> findByStatus(Status status);


}
