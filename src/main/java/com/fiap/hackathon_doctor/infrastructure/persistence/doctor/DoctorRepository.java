package com.fiap.hackathon_doctor.infrastructure.persistence.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, String>, JpaSpecificationExecutor<DoctorEntity> {

	Optional<DoctorEntity> findByEmail(String email);

	Optional<DoctorEntity> findById(UUID s);

}
