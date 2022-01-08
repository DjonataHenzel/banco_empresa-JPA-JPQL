package com.example.demo.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Departamento;
import com.example.demo.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
//	// JPA
	Funcionario findByNomeAndDependentes(String nome, Integer qt_dependentes);

	Funcionario findFirstByOrderBySalarioDesc();

	List<Funcionario> findTop3ByOrderBySalarioDesc();

//	//Query
	@Query("from Funcionario where qtDependente = 0 order by nome")
	List<Funcionario> funcSDep();

	@Query(name = "Funcionario.byNomeLike")
	List<Funcionario> nomeContem(String nome);

	@Query(name = "Funcionario.byDependentes")
	List<Funcionario> funcDependente(Integer dependente);

	@Query("from Funcionario where salario > :salario")
	List<Funcionario> findBySalarioGreaterThan(@Param("salario") BigDecimal bigDecimal);

	@Query(value = "select * from funcionario where salario > :salario", nativeQuery = true)
	List<Funcionario> maiorSal(@Param("salario") BigDecimal bigDecimal);

	    @Query("from Funcionario where departamento.id = :id")
	    List<Funcionario> todosFuncionariosPorDepart(@Param("id") Integer departamento);

	//	----------------------------------Atividade 7--------------------------------- 
	//Exercicio 1
	@Procedure("reajuste_salarial")
	void ajusteSalarioTodosFuncionario(Integer porcentagemReajuste);   
	    
	//Exercicio 2 
	@Query("select f form Funcionario f inner join Departamento d on f.departamento = d.id where f.qtDependente = 0 and d.nome = :departamento")
	List<Funcionario> funcDepartamentoSemDependente(@Param("departamento") String departamento);

	//Exercicio 3
	@Modifying
	@Transactional
	@Query(value = "update Funcionario set departamento_id = ?1 where departamento_id = ?2")
	int alteracaoParaNovoDepartamento(@Param("departamentoNovo") Integer departamentoNovo,@Param("departamentoAntigo")Integer departamentoAntigo);

	// Exercicio 4
	 @Modifying
	 @Transactional
	 @Query("delete from Funcionario where departamento_id = ?1")
	 int removerTodosFuncionariosPorDepartamento(Integer departamento);
}
