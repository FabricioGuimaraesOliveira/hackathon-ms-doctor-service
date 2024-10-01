package com.fiap.hackathon_doctor.entity.doctor.gateway;

import com.fiap.hackathon_doctor.entity.doctor.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface DoctorGateway {

	Doctor salvar(Doctor doctor);

	Optional<Doctor> buscarDoctorPorEmail(String email);

	Page<Doctor> listarPaginado(Pageable pageable);

	Optional<Doctor> buscarDoctorPorId(UUID id);

}
