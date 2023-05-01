package employeer.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import employeer.model.Funcionario;
import employeer.restAPI.RestApi;

/** classe para auxiliar com o map onde a chave é o cargo e o valor é uma list com os nomes
 * dos funcionários. Auxilia também no incremento da remuneração.
 */
public class MapAndIncRemunaration {

  /** map key job: List<nome funcionário>. */
  private Map<String, List<String>> mapJobAndPeople = new LinkedHashMap<>();
  
  /** instância RestApi.*/
  private RestApi crud = new RestApi();
  
  /** construtor, populando o map.*/
  public MapAndIncRemunaration () {
    this.populateMap();
  }

  /** método para popular o map. */
  final void populateMap() {
    List<Funcionario> tableEmployeersData = this.crud.getAllFunc();
    
    for(Funcionario employeer : tableEmployeersData) {
      String job = employeer.getJob();
      String name = employeer.getName();
      if (!this.getMapJobAndPeople().containsKey(job)) {
        this.mapJobAndPeople.put(job, new ArrayList<String>());
      }
      this.mapJobAndPeople.get(job).add(name);
    }
  }
  
  /** método para dar um update na remuneração. */
  public void incRemuneration(int inc) {
    double porcent = 1 + inc * 0.01;
    List<Funcionario> tableEmployeersData = this.crud.getAllFunc();
    
    tableEmployeersData.stream()
        .forEach(employeer -> {
           BigDecimal newRemuneration = new BigDecimal(employeer.getRemuneration().longValue() * porcent);
           crud.updateRemunaration(employeer.getId(), newRemuneration);
        });
  }
  
  /** getter do map.*/
  public Map<String, List<String>> getMapJobAndPeople() {
    return mapJobAndPeople;
  }
}