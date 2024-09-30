package com.fiap.hackathon_doctor.infrastructure.doctor.gateway;

import com.fiap.hackathon_doctor.entity.doctor.gateway.DoctorGateway;
import com.fiap.hackathon_doctor.entity.doctor.model.Doctor;
import com.fiap.hackathon_doctor.infrastructure.persistence.doctor.DoctorEntity;
import com.fiap.hackathon_doctor.infrastructure.persistence.doctor.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public class DoctorDataBaseRepository implements DoctorGateway {

	private final DoctorRepository doctorRepository;

	private final ModelMapper modelMapper;

	public DoctorDataBaseRepository(DoctorRepository doctorRepository, ModelMapper modelMapper) {
		this.doctorRepository = doctorRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	@Transactional()
	public Doctor salvar(Doctor doctor) {
		DoctorEntity doctorEntity = modelMapper.map(doctor, DoctorEntity.class);
		doctorRepository.saveAndFlush(doctorEntity);
		return modelMapper.map(doctorEntity, Doctor.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Doctor> buscarDoctorPorEmail(String email) {
		return doctorRepository.findByEmail(email).map(doctorEntity -> modelMapper.map(doctorEntity, Doctor.class));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Doctor> listarPaginado(Pageable pageable) {
		Page<DoctorEntity> doctorEntitiesPage = doctorRepository.findAll(pageable);
		return doctorEntitiesPage.map(doctorEntity -> modelMapper.map(doctorEntity, Doctor.class));
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Doctor> buscarDoctorPorId(UUID id) {
		return doctorRepository.findById(id).map(doctorEntity -> modelMapper.map(doctorEntity, Doctor.class));
	}

}
