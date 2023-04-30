package employeer.projedataRequest;

import java.util.ArrayList;
import java.util.List;

import employeer.model.Funcionario;
import employeer.restAPI.RestApi;
import employeer.util.ToPrint;

/** class para retornar strings para criar a tabela. */
public class Table {

    /** itens da tabela de funcionários. */
    public List<String> tableRows() {
        RestApi crud = new RestApi();
        ToPrint print = new ToPrint();
        List<Funcionario> employeers = crud.getAllFunc();
        
        List<String> emp = new ArrayList<>();
        for (Funcionario e : employeers) {
            String birthdate = print.birthdateDayMonthYear(e.getBirthDate());
            String remuneration = print.remunerationToString(e.getRemuneration());
            emp.add(String.format("%10s %18s %12s %20s \n", e.getName(), birthdate, remuneration, e.getJob()));
        }
        return emp;
    }
    
    /** titulo da tabela. */
    public String titleTable() {
        return String.format("%10s %20s %10s %20s \n", "Nome", "Data Nascimento", "Salário", "Função");
    }
    
    /** divisa título e itens. */
    public String divTitleAndItens() {
        return "------------------------------------------------------------------\n";
    }
}
