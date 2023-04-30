package employeer;

import employeer.model.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/** class para popular a tabela. */
public class Seed {
  
  /** constructor, chama o método deleteIfExistTable. */
  public Seed() {
    this.deleteIfExistTable();
  }

  /** método para deletar a tabela se ja existir. */
  final void deleteIfExistTable() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-employeers");
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      @SuppressWarnings("unchecked")
      List<Funcionario> tableValues = em.createQuery("FROM Funcionario").getResultList();
      if(tableValues.size() > 0) {
    	  em.createNativeQuery("DROP TABLE Funcionario").executeUpdate();
      }
      this.insertSeed();
      } catch(PersistenceException e) {
        e.printStackTrace();
      } finally {
    	  em.close();
      }
  }

  /** método para inserir os dados na tabela.*/
  final void insertSeed() {
    Funcionario maria = new Funcionario();
    BigDecimal mariaRe = new BigDecimal(2009.44);
    maria.setRemuneration(mariaRe);
    maria.setBirthDate(LocalDate.of(2000, 10, 18));
    maria.setName("Maria");
    maria.setJob("Operador");
    Funcionario joao = new Funcionario();
    BigDecimal joaoRe = new BigDecimal(2284.38);
    joao.setRemuneration(joaoRe);
    joao.setBirthDate(LocalDate.of(1990, 5, 12));
    joao.setName("João");
    joao.setJob("Operador");
    Funcionario caio = new Funcionario();
    BigDecimal caioRe = new BigDecimal(9836.14);
    caio.setRemuneration(caioRe);
    caio.setBirthDate(LocalDate.of(1961, 5, 2));
    caio.setName("Caio");
    caio.setJob("Coordenador");
    Funcionario miguel = new Funcionario();
    BigDecimal miguelRe = new BigDecimal(19119.88);
    miguel.setRemuneration(miguelRe);
    miguel.setBirthDate(LocalDate.of(1988, 10, 14));
    miguel.setName("Miguel");
    miguel.setJob("Diretor");
    Funcionario alice = new Funcionario();
    BigDecimal aliceRe = new BigDecimal(2234.68);
    alice.setRemuneration(aliceRe);
    alice.setBirthDate(LocalDate.of(1995, 1, 5));
    alice.setName("Alice");
    alice.setJob("Recepcionista");
    Funcionario heitor = new Funcionario();
    BigDecimal heitorRe = new BigDecimal(1582.72);
    heitor.setRemuneration(heitorRe);
    heitor.setBirthDate(LocalDate.of(1999, 11, 19));
    heitor.setName("Heitor");
    heitor.setJob("Operador");
    Funcionario arthur = new Funcionario();
    BigDecimal arthurRe = new BigDecimal(4071.84);
    arthur.setRemuneration(arthurRe);
    arthur.setBirthDate(LocalDate.of(1993, 3, 31));
    arthur.setName("Arthur");
    arthur.setJob("Contador");
    Funcionario laura = new Funcionario();
    BigDecimal lauraRe = new BigDecimal(3017.45);
    laura.setRemuneration(lauraRe);
    laura.setBirthDate(LocalDate.of(1994, 7, 8));
    laura.setName("Laura");
    laura.setJob("Gerente");
    Funcionario heloisa = new Funcionario();
    BigDecimal heloisaRe = new BigDecimal(1606.85);
    heloisa.setRemuneration(heloisaRe);
    heloisa.setBirthDate(LocalDate.of(2003, 5, 24));
    heloisa.setName("Heloisa");
    heloisa.setJob("Eletricista");
    Funcionario helena = new Funcionario();
    BigDecimal helenaRe = new BigDecimal(2799.93);
    helena.setRemuneration(helenaRe);
    helena.setBirthDate(LocalDate.of(1996, 9, 2));
    helena.setName("Helena");
    helena.setJob("Gerente");

    List<Funcionario> funcionarios = new ArrayList<>();
    funcionarios.add(maria);
    funcionarios.add(joao);
    funcionarios.add(caio);
    funcionarios.add(miguel);
    funcionarios.add(alice);
    funcionarios.add(heitor);
    funcionarios.add(arthur);
    funcionarios.add(laura);
    funcionarios.add(heloisa);
    funcionarios.add(helena);
      
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-employeers");
    EntityManager em = emf.createEntityManager();
      
    try {
      em.getTransaction().begin();
      for(Funcionario funcionario : funcionarios) {
        em.persist(funcionario);
      }
      em.getTransaction().commit();
    } catch (Exception e) {
      em.getTransaction().rollback();
    } finally {
    	em.close();
    }
  }
}
