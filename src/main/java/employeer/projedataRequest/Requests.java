package employeer.projedataRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import employeer.model.Funcionario;
import employeer.restAPI.RestApi;
import employeer.util.MapAndIncRemunaration;
import employeer.util.ToPrint;

/** class para os requisitos.*/
public class Requests {
   /** propriedade para acessar o banco. */
   final RestApi crud;
   
   /** propriedade para acessar util MapAndIncRemuneration. */
   final MapAndIncRemunaration mapAndInc;
   
   /** propriedade para acessar util ToPrint. */
   final ToPrint utilPrint;
   
   /** constructor atribuindo propriedades. */
   public Requests() {
       this.crud = new RestApi();
       this.mapAndInc = new MapAndIncRemunaration();
       this.utilPrint = new ToPrint();
   }
   
   /** requisito 3.2 remover joão. */
   public void deleteJoao() {
       Funcionario joao = this.crud.getByName("João");
       this.crud.delete(joao.getId());
   }
   
   /** requisito 3.4 aumentar o salário dos funcionários em 10%. */
   public void incRemuneration() {
       this.mapAndInc.incRemuneration(10);
   }
   
   /** requisito 3.5 agrupar em um map  função é lista de funcionario. */
   public Map<String, List<String>> jobAndPeople() {
	   return this.mapAndInc.getMapJobAndPeople();
   }
   
   /** requisito 3.8 map com as pessoas fazendo aniversário mes 10, outubro, e mes 12, dezembro. */
   public Map<String, List<String>> monthAndPeople() {
       this.utilPrint.setEmployeerInMonth(10);
       this.utilPrint.setEmployeerInMonth(12);
       return this.utilPrint.getPersonInMonth();
   }
   
   /** requisito 3.9 pessoa mais velha. */
   public String oldest() {
	   return this.utilPrint.findOlder();
   }
   
   /** requisito 3.10 funcionários ordenado, retornando apenas o nome. */
   public List<String> sort() {
	   return this.utilPrint.sort();
   }
   
   /** requisito 3.11 retornar total do valor funcionario. */
   public String total() {
       BigDecimal total = this.utilPrint.allRemuneration();
       return this.utilPrint.remunerationToString(total);
   }
   
   /** requisito 3.12 retornar número de salário de cada funcionário, nome e o valor. */
   public List<String> numberOfRemunerations() {
        List<Funcionario> employeers = this.crud.getAllFunc();
        
        List<String> nameAndRemunerations = employeers.stream()
        		.map(employeer -> this.utilPrint.numberRemuneration(employeer, 1212))
        		.collect(Collectors.toList());
        
        return nameAndRemunerations;
   }
}
