package com.example.demo;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.Departamento;
import com.example.demo.entity.Funcionario;
import com.example.demo.service.DepartamentoService;
import com.example.demo.service.FuncionarioService;

@SpringBootApplication
public class DemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(DepartamentoService departamentoService, FuncionarioService funcionarioService) {
		return (arg) -> {
			log.info("-----------------Atividade 05----------------------");
			log.info("---------Mostrar todos os departamentos------------");
			log.info("---------------------------------------------------");
			for (Departamento departamento : departamentoService.buscarTodosDepar()) {
		    log.info(departamento.toString());
		    log.info("");
			}

			log.info("---------Mostrar todos funcionarios------------");
			log.info("---------------------------------------------------");
			for (Funcionario funcionario : funcionarioService.buscarTodosFunc()) {
				log.info(funcionario.toString());
				log.info("");
			}
			

     		log.info("-----------------Atividade 06----------------------");
			log.info("------Mostrar funcionario por nome e quantidade de dependentes - PALAVRA-CHAVE-------");
			log.info("-------------------------------------------------------------------------------------");
			Funcionario funcionary = funcionarioService.funcNomeDepend("Afonso Venites", 3);
			log.info(funcionary.toString());
			log.info("");

			log.info("---------Mostrar todos funcionarios de um departamento - JPQL VIA @ query-------------");
			log.info("--------------------------------------------------------------------------------------");
			for (Funcionario funcionario : funcionarioService.todosFuncinariosDep(4)) {
			log.info(funcionary.toString());
			log.info("");
			}

			log.info("---------------Mostrar primeiro departamento cadastrado-------------------------------");
			log.info("--------------------------------------------------------------------------------------");
			log.info(departamentoService.primeiroDepart().toString());
			log.info("");

			log.info("----------------Mostrar funcionario com maior salario----------------------------------");
			log.info("---------------------------------------------------------------------------------------");
			log.info(funcionarioService.funcMaiorSal().toString());
			log.info("");

			log.info("------------------Mostrar 3 primeiros funcionarios com maior salario-------------------");
			log.info("---------------------------------------------------------------------------------------");
			for (Funcionario funcionario : funcionarioService.funcTresMaiorSal()) {
			log.info(funcionario.toString());
			log.info("");
			}

			log.info("----------Mostrar funcionario sem dependente por ordem crescente por nome - JPQL ------");
			log.info("---------------------------------------------------------------------------------------");
			for (Funcionario funcionario : funcionarioService.funcSemDepend()) {
			log.info(funcionario.toString());
			log.info("");
			}

			log.info("--------Mostrar funcionario que tem o salario maior que o valor informado - JPQL-------");
			log.info("---------------------------------------------------------------------------------------");
			for (Funcionario funcionario : funcionarioService.funcSalarioAcima(new BigDecimal(1700))) {
			log.info(funcionario.toString());
			log.info("");
			}

			log.info("---Mostrar funcionario com salario maior que um determinado -JPQL - Sobrescrevendo palavras- chaves ---");
			log.info("----------------------------------------------------------------------------------------");
			for (Funcionario funcionario : funcionarioService.salarioAcimaNativeQuery(new BigDecimal(1800))) {
			log.info(funcionario.toString());
			log.info("");
			}

			log.info("--------Mostrar funcionarios por quantidade de dependente - Consulta @NamedQuery.------");
			log.info("---------------------------------------------------------------------------------------");
			for (Funcionario funcionario : funcionarioService.funcQtDependente(3)) {
			log.info(funcionario.toString());
			log.info("");
			}

			log.info("-----------Mostrar funcionario onde o nome contem %XX% - NamedNativeQuery--------------");
			log.info("---------------------------------------------------------------------------------------");
			for (Funcionario funcionario : funcionarioService.nomeParcial("%iego%")) {
			log.info(funcionario.toString());
			log.info("");
			}
			
				
			log.info("-----------------------------------Atividade 07-----------------------------------------");
			log.info("-------1-Aumentar salarios de todos funcionarios sobre um valor em porcentagem----------");
            log.info("----------------------------------------------------------------------------------------");
            funcionarioService.ajustarSalarioFuncionarios(Integer.valueOf(28));
            log.info("");
            		
			log.info("--------2-Mostrar todos funcionario de determinado departamento sem dependentes---------");
			log.info("----------------------------------------------------------------------------------------");
			for (Funcionario funcionario : funcionarioService.funcionarioSemDependentePorDepartamento("Informatica")) {
			log.info(funcionario.toString());
			log.info("");
			}
			
			log.info("----------------3-Alterar funcionario de um departamento para outro (Update)------------");
	        log.info("----------------------------------------------------------------------------------------");
	        Integer armazenarRetornoUpdate = funcionarioService.alterarDepartamento(3, 4);
	        log.info(armazenarRetornoUpdate.toString());
	        log.info("");

	        log.info("----------------------4- Delete todos funcionarios de um departamento-------------------");
            log.info("----------------------------------------------------------------------------------------");
            Integer armazenarRetornoDelete = funcionarioService.apagarTodosFuncionarioDoDepartamento(3);
            log.info(armazenarRetornoDelete.toString());
            log.info("");
			
		  //------------------------------------Atividade 08------------------------------------------------
		  log.info("----------------------Salvar departamento com funcionario atrelado----------------------");
          log.info("----------------------------------------------------------------------------------------");
			Departamento departamento = new Departamento();
			Funcionario funcionario = new Funcionario();
			departamento.setNome_departamento("Mecanica");
			funcionario.setCargo("Zelador");
			funcionario.setNome("Adriano Gonçalves Junior");
			funcionario.setQtDependente(1);
			funcionario.setSalario(2385.00);
			funcionario.setDerpartamento(departamento);
			departamentoService.salvarFuncionarioDepartamento(departamento, funcionario);
			log.info("");
		};
	}
}
