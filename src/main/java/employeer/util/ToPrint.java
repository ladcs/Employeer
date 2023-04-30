package employeer.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import employeer.model.Funcionario;
import employeer.restAPI.RestApi;

/** class para transformar as informações em strings. */
public class ToPrint {
	
	/** propriedade map para informar as pessoas nascidas nos meses já enviado para o método. */
    private Map<String, List<String>> personInMonth = new HashMap<String, List<String>>();
	
    /** propriedade string com nomes dos meses. */
	final String[] nameMonth = {
          "janeiro",
          "fevereiro",
          "março",
          "abril",
          "maio",
          "junho",
          "julho",
          "agosto",
          "setembro",
          "outubro",
          "novembro",
          "dezembro"  
    };
	
	/** propriedade para conversar com API. */
	final RestApi crud;
	
	/** constructor para criar a comunicação com a API. */
	public ToPrint() {
		this.crud = new RestApi();
	}
	
	/** formata BigDecimal para uma string com . para milhar e , para decimal. */
    public String remunerationToString(BigDecimal valueToString) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(valueToString);
    }
    
    /** formata localDate para dd/MM/yyyy. */
    public String setBirthdateDayMonthYear(LocalDate birthdateToString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return birthdateToString.format(formatter);
      }
    
    /** retorna a propriedade personInMonth. */
    public Map<String, List<String>> getPersonInMonth() {
		return personInMonth;
	}

    /** adiciona o mês como chave e as pessoas nascida nesse mês no map personInMonth. */
	public void getEmployeerInMonth(int month) {
    	List<Funcionario> tableEmployeersData = crud.getAllFunc();
    	
        List<String> employeersName = tableEmployeersData.stream()
    	          .filter(employeer -> employeer.getBirthDate().getMonthValue() == month)
    	          .map(Funcionario::getName)
    	          .collect(Collectors.toList());
        
        this.personInMonth.put(this.nameMonth[month - 1], employeersName);
    }
	
	/** método para buscar o employeer mais velho, retornando o nome e a idade do mesmo. */	
	public String findOlder() {
        List<Funcionario> tableEmployeersData = crud.getAllFunc();
        
        Funcionario oldestEmployeer = tableEmployeersData.stream()
             .min(Comparator.comparing(Funcionario::getBirthDate))
             .orElse(null);
        
		LocalDate toDay = LocalDate.now();
		int ageOfOldestEmployeer = Period.between(oldestEmployeer.getBirthDate(), toDay).getYears();
		
		String retunNameAndAge = oldestEmployeer.getName() + " possui " + ageOfOldestEmployeer + " anos.";
        
		return retunNameAndAge;
    }
	
	/** retorna a os nomes dos employeers ordenado. */
	public List<String> order(){
        List<Funcionario> tableEmployeersData = crud.getAllFunc();
        
        return tableEmployeersData.stream()
                .map(Funcionario::getName)
                .sorted()
                .collect(Collectors.toList());
	}
	
	/** retorna o valor de todos os salários somados. */
	public BigDecimal allRemuneration() {
		List<Funcionario> tableEmployeersData = crud.getAllFunc();
		
		return tableEmployeersData.stream()
                .map(Funcionario::getRemuneration)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	/** retorna o nome e quantos salários ele ganha. */
	public String numberRemuneration(Funcionario employeer, double min) {
		BigDecimal multiply = new BigDecimal(1 / min);
		int numberRemuneration = employeer.getRemuneration().multiply(multiply).intValue();
		if (numberRemuneration > 1) {
			return employeer.getName() + " recebe " + numberRemuneration + " salários.";
		}
		return employeer.getName() + " recebe " + numberRemuneration + " salário."; 
	}
}