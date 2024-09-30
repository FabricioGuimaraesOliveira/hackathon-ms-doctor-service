package com.fiap.hackathon_doctor.entity.doctor.model;

import com.fiap.hackathon_doctor.entity.doctorcalendar.model.DoctorCalendar;
import com.google.gson.Gson;

import java.util.List;
import java.util.UUID;

public class Doctor {

	private UUID id;

	private String nome;

	private String cpf;

	private String numeroCrm;

	private String email;

	private String senha;

	private String especialidade;

	private List<DoctorCalendar> horariosDisponiveis;

	public Doctor(UUID id, String nome, String cpf, String numeroCrm, String email, String senha,
			String especialidade) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.numeroCrm = numeroCrm;
		this.email = email;
		this.senha = senha;
		this.especialidade = especialidade;
	}

	public UUID getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNumeroCrm() {
		return numeroCrm;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public void validarCadastro() {
		if (this.cpf == null || this.cpf.isEmpty()) {
			throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
		}
		if (this.nome == null || this.nome.isEmpty()) {
			throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
		}
		if (this.email == null || this.email.isEmpty()) {
			throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
		}
		if (this.numeroCrm == null || this.numeroCrm.isEmpty()) {
			throw new IllegalArgumentException("CRM não pode ser nulo ou vazio");
		}
		if (this.senha == null || this.senha.isEmpty()) {
			throw new IllegalArgumentException("Senha não pode ser nulo ou vazio");
		}

		if (this.especialidade == null || this.especialidade.isEmpty()) {
			throw new IllegalArgumentException("Especialidade não pode ser nulo ou vazio");
		}
	}

	public void atualizar(String nome, String email, String numeroCrm, String senha, String especialidade) {
		if (nome == null) {
			throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
		}
		if (email == null) {
			throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
		}
		if (numeroCrm == null) {
			throw new IllegalArgumentException("CRM não pode ser nulo ou vazio");
		}
		if (senha == null) {
			throw new IllegalArgumentException("Senha não pode ser nulo ou vazio");
		}
		this.nome = nome;
		this.email = email;
		this.numeroCrm = numeroCrm;
		this.senha = senha;
		this.especialidade = especialidade;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNumeroCrm(String numeroCrm) {
		this.numeroCrm = numeroCrm;
	}

}
