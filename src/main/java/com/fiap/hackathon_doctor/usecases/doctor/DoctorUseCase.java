package com.fiap.hackathon_doctor.usecases.doctor;

import com.fiap.hackathon_doctor.entity.doctor.gateway.DoctorCpfGateway;
import com.fiap.hackathon_doctor.entity.doctor.gateway.DoctorEmailGateway;
import com.fiap.hackathon_doctor.entity.doctor.gateway.DoctorGateway;
import com.fiap.hackathon_doctor.entity.doctor.model.Doctor;
import com.fiap.hackathon_doctor.entity.doctor.validator.CpfValidator;
import com.fiap.hackathon_doctor.infrastructure.doctor.exceptions.ResourceNotFoundException;

import java.util.UUID;

public class DoctorUseCase {

	final private DoctorGateway doctorGateway;

	public DoctorUseCase(DoctorGateway doctorGateway) {
		this.doctorGateway = doctorGateway;
	}

	public Doctor buscarPorEmail(String email) {
		return doctorGateway.buscarDoctorPorEmail(email)
			.orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado para o e-mail: " + email));
	}

	public Doctor buscarPorId(UUID id) {
		return doctorGateway.buscarDoctorPorId(id)
			.orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado para o id: " + id));
	}

	public Doctor cadastrar(Doctor doctor) {
		doctor.setCpf(CpfValidator.sanitizar(doctor.getCpf()));
		doctor.validarCadastro();
		var doctorEntity = doctorGateway.salvar(doctor);
		return doctorEntity;
	}

	public Doctor atualizar(UUID id, Doctor request) {
		var doctor = doctorGateway.buscarDoctorPorId(id)
			.orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado para o id: " + id));
		doctor.atualizar(request.getNome(), request.getEmail(), request.getNumeroCrm(), request.getSenha(),
				request.getEspecialidade());
		return doctorGateway.salvar(doctor);
	}

}
