package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import lombok.Data;

@Entity
@Data
@NamedQuery(name = "Funcionario.byDependentes", query = "from Funcionario where qtd_depententes = ?1")
@NamedNativeQuery(name = "Funcionario.byNomeLike", query = "select * from funcionario where nome like ?1", resultClass = Funcionario.class)
@NamedStoredProcedureQuery(name = "Funcionario.ajuste.salario", procedureName = "reajuste_salarial",parameters = {@StoredProcedureParameter(
						mode = ParameterMode.IN,name = "in_valorAumentoPorcentagem",type = Integer.class)})

public class Funcionario{

	@Id
	@GeneratedValue
	Long id;

	@Column(name = "nome", nullable = false, length = 70)
	private String nome;

	@Column(name = "qtDependente", nullable = false)
	private Integer qtDependente;

	@Column(name = "salario", nullable = false)
	private Double salario;

	@Column(name = "cargo", nullable = false)
	private String cargo;

	// Relacionamento
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "departamento_id", nullable = false)
	private Departamento derpartamento;

	@Override
	public String toString() {
		return "Funcionario -> id= " + this.getId() + " - Nome= " + this.getNome() + " - Quantidade Dependente= "
				+ this.getQtDependente() + " - Salario= " + this.getSalario() + " - Cargo= " + this.getCargo()
				+ " - Departamento= " + this.derpartamento.getNome_departamento();
	}
}
