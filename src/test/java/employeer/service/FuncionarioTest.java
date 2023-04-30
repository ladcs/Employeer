package employeer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import employeer.model.Funcionario;

public class FuncionarioTest {
		@Test
		  @DisplayName("Testa se cria pessoa e consegue atribuir valores com setter do atributo.")
		  void settersTests() {
		   BigDecimal e1Remuneration = new BigDecimal(1000.00);
		   BigDecimal e2Remuneration = new BigDecimal(1500.00);
		   BigDecimal e3Remuneration = new BigDecimal(300.00);
	       Funcionario e1 = new Funcionario();
	       e1.setName("Garus");
	       e1.setBirthDate(LocalDate.of(1988, 9, 20));
	       e1.setRemuneration(e1Remuneration);
	       e1.setJob("mecanico");
	       Funcionario e2 = new Funcionario();
	       e2.setName("Rafael");
	       e2.setBirthDate(LocalDate.of(1994, 3, 5));
	       e2.setRemuneration(e2Remuneration);
	       e2.setJob("programador");
	       Funcionario e3 = new Funcionario();
	       e3.setName("Luciano");
	       e3.setBirthDate(LocalDate.of(1992, 2, 19));
	       e3.setRemuneration(e3Remuneration);
	       e3.setJob("auxiliar");
		      
	       assertEquals(e1.getName(), "Garus");
	       assertEquals(e1.getBirthDate(), LocalDate.of(1988, 9, 20));
	       assertEquals(e1.getRemuneration(), e1Remuneration);
	       assertEquals(e1.getJob(), "mecanico");
	       assertEquals(e2.getName(), "Rafael");
	       assertEquals(e2.getBirthDate(), LocalDate.of(1994, 3, 5));
	       assertEquals(e2.getRemuneration(), e2Remuneration);
	       assertEquals(e2.getJob(), "programador");
	       assertEquals(e3.getName(), "Luciano");
	       assertEquals(e3.getBirthDate(), LocalDate.of(1992, 2, 19));
	       assertEquals(e3.getRemuneration(), e3Remuneration);
	       assertEquals(e3.getJob(), "auxiliar");
		  }
	}
