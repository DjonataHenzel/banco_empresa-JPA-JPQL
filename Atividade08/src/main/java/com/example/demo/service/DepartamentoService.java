package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Departamento;
import com.example.demo.entity.Funcionario;
import com.example.demo.repository.DepartamentoRepository;
import com.example.demo.repository.FuncionarioRepository;

@Service
public class DepartamentoService {
	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public DepartamentoService(DepartamentoRepository departamentoRepository) {
		this.departamentoRepository = departamentoRepository;
	}

	public void salvar(Departamento departamento) {
		departamentoRepository.save(departamento);
	}

	public Optional<Departamento> buscarPorId(Long id) {
		return departamentoRepository.findById(id);
	}

	public List<Departamento> buscarTodosDepar() {
		return departamentoRepository.findAll();
	}

	public Departamento primeiroDepart() {
		return departamentoRepository.findFirstOrderByNomeAsc();
	}
	
	@Transactional(readOnly = false)
	public void salvarFuncionarioDepartamento(Departamento departamento, Funcionario funcionario) {
		departamentoRepository.save(departamento);
		funcionarioRepository.save(funcionario);
	}
}
