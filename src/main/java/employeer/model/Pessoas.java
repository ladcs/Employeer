package employeer.model;

import java.time.LocalDate;

import javax.persistence.MappedSuperclass;

/**
 * class Pessoa, base da class Funcionario. 
 */
@MappedSuperclass
public class Pessoas {
	
	/** criando coluna name, referente ao nome. */
	private String name;
	
	/** criando coluna birthDate, referente a data de nascimento. */
	private LocalDate birthDate;
	
	/** getters e setters das colunas. */
	/** retorna name. */
	public String getName() {
		return name;
	}
	
	/** escreve name. */
	public void setName(String name) {
		this.name = name;
	}
	
	/** retorna birthDate. */
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	/** escreve birthDate. */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
}