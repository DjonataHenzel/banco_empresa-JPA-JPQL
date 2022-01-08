package com.example.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Departamento {
	@Id
	@GeneratedValue
	Long id;
	
	@Column (nullable = false, length = 50)
	private String nome_departamento;

	@Override
	public String toString() {
		return "Departamento -> id= " + this.getId() + " - Nome= " + this.getNome_departamento();
	}
}
