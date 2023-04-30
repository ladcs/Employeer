package employeer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import employeer.model.Pessoas;

public class PessoasTest {
	@Test
	@DisplayName("Testa se cria pessoa e consegue atribuir valores com setter do atributo.")
	void settersTest() {
      Pessoas p1 = new Pessoas();
      p1.setName("Garus");
      p1.setBirthDate(LocalDate.of(1988, 9, 20));
      Pessoas p2 = new Pessoas();
      p2.setName("Rafael");
      p2.setBirthDate(LocalDate.of(1994, 3, 5));
      Pessoas p3 = new Pessoas();
      p3.setName("Luciano");
      p3.setBirthDate(LocalDate.of(1992, 2, 19));
      
      assertEquals(p1.getName(), "Garus");
      assertEquals(p1.getBirthDate(), LocalDate.of(1988, 9, 20));
      assertEquals(p2.getName(), "Rafael");
      assertEquals(p2.getBirthDate(), LocalDate.of(1994, 3, 5));
      assertEquals(p3.getName(), "Luciano");
      assertEquals(p3.getBirthDate(), LocalDate.of(1992, 2, 19));
    }
}