package employeer.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** Class para criar a entidade do hibernate. Class Funcionario que extende class Pessoas. */
@Entity
public class Funcionario extends Pessoas {
	
	
	/** criando coluna Id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/** constructior para poder ter as colunas da class Pessoas. */
	public Funcionario() {
		super();
	}
	
	/** criando coluna remuneration, que é referente ao salário. */
	private BigDecimal remuneration;
	
	/** criando coluna job, que é referente a função do funcionário. */
	private String job;

	/** getters e setters das colunas.*/
	
	/** para retornar o Id. */
	public Long getId() {
		return id;
	}
	
	/** para retornar o salário. */
	public BigDecimal getRemuneration() {
		return remuneration;
	}
	
	/** para escrever em remuneration. */
	public void setRemuneration(BigDecimal remuneration) {
		this.remuneration = remuneration;
	}

	/** para retornar a coluna job. */
	public String getJob() {
		return job;
	}

	/** para escrever em job. */
	public void setJob(String job) {
		this.job = job;
	}
}
