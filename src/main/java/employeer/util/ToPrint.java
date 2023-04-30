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

public class ToPrint {
	
private Map<String, List<String>> personInMonth = new HashMap<String, List<String>>();
	
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
	
	final RestApi crud;
	
	public ToPrint() {
		this.crud = new RestApi();
	}
		
    public String remunerationToString(BigDecimal valueToString) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(valueToString);
    }
    
    public String setBirthdateDayMonthYear(LocalDate birthdateToString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return birthdateToString.format(formatter);
      }
    
    public Map<String, List<String>> getPersonInMonth() {
		return personInMonth;
	}

	public void getEmployeerInMonth(int month) {
    	List<Funcionario> tableEmployeersData = crud.getAllFunc();
    	
        List<String> employeersName = tableEmployeersData.stream()
    	          .filter(employeer -> employeer.getBirthDate().getMonthValue() == month)
    	          .map(Funcionario::getName)
    	          .collect(Collectors.toList());
        
        this.personInMonth.put(this.nameMonth[month - 1], employeersName);
    }
	
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
	
	public List<String> order(){
        List<Funcionario> tableEmployeersData = crud.getAllFunc();
        
        return tableEmployeersData.stream()
                .map(Funcionario::getName)
                .sorted()
                .collect(Collectors.toList());
	}
	
	public BigDecimal allRemuneration() {
		List<Funcionario> tableEmployeersData = crud.getAllFunc();
		
		return tableEmployeersData.stream()
                .map(Funcionario::getRemuneration)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	public String numberRemuneration(Funcionario employeer, double min) {
		BigDecimal multiply = new BigDecimal(1 / min);
		int numberRemuneration = employeer.getRemuneration().multiply(multiply).intValue();
		if (numberRemuneration > 1) {
			return employeer.getName() + " recebe " + numberRemuneration + " salários.";
		}
		return employeer.getName() + " recebe " + numberRemuneration + " salário."; 
	}
}