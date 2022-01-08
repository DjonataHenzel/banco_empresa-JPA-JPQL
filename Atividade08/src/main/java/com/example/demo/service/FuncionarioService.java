package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Departamento;
import com.example.demo.entity.Funcionario;
import com.example.demo.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public void salvar(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
	}
	
	public Optional<Funcionario> buscaPorId(Long id) {
		return funcionarioRepository.findById(id);
	}

	public List<Funcionario> buscarTodosFunc() {
		return funcionarioRepository.findAll();
	}


	public List<Funcionario> funcSalarioAcima(BigDecimal bigDecimal) {
		return funcionarioRepository.findBySalarioGreaterThan(bigDecimal);
	}

	public Funcionario funcNomeDepend(String nomeFun, Integer qtdDependente) {

		return funcionarioRepository.findByNomeAndDependentes(nomeFun, qtdDependente);
	}

	public List<Funcionario> nomeParcial(String nome) {
		return funcionarioRepository.nomeContem(nome);
	}

	public List<Funcionario> buscarTodosPorIdadeAsc() {

		return funcionarioRepository.findAll(Sort.by(Sort.Direction.ASC, "idade"));
	}

	public List<Funcionario> funcSemDepend() {
		return funcionarioRepository.funcSDep();
	}

	public List<Funcionario> todosFuncinariosDep(Integer departamento) {

		return funcionarioRepository.todosFuncionariosPorDepart(departamento);
	}

	public Funcionario funcMaiorSal() {
		return funcionarioRepository.findFirstByOrderBySalarioDesc();
	}

	public List<Funcionario> funcTresMaiorSal() {
		return funcionarioRepository.findTop3ByOrderBySalarioDesc();
	}

	public List<Funcionario> funcQtDependente(Integer dependente) {
		return funcionarioRepository.funcDependente(dependente);
	}

	public List<Funcionario> salarioAcimaNativeQuery(BigDecimal bigDecimal) {
		return funcionarioRepository.maiorSal(bigDecimal);
	}
	//------------------------------Atividade 07-----------------------------
	// Exercicio 1
	 public void ajustarSalarioFuncionarios (Integer porcentagemReajuste) {
	   funcionarioRepository.ajusteSalarioTodosFuncionario(porcentagemReajuste);
	 }
	
	//Exercicio 2
	public List<Funcionario> funcionarioSemDependentePorDepartamento (String departamento){
		return funcionarioRepository.funcDepartamentoSemDependente(departamento);
	}
	
	//Exercicio 3 
	public int alterarDepartamento(Integer departamentoNovo, Integer departamentoAntigo) {
    	return funcionarioRepository.alteracaoParaNovoDepartamento(departamentoNovo, departamentoAntigo);
    }
	
	// Exercicio 4 
	 public int apagarTodosFuncionarioDoDepartamento(Integer departamento) {
	    return funcionarioRepository.removerTodosFuncionariosPorDepartamento(departamento);
	}

}
