package employeer.service;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import employeer.model.Funcionario;
/** class EmployeerService, responsável por se comunicar com o banco a partir do Hibernate.
 * infelizmente não sei testar essa parte.
 */
public class EmployeerService {
	
	/** método para pegar um funcionário por id. */
	public Funcionario findById (Long id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-employeers");
        EntityManager em = emf.createEntityManager();
        
        Funcionario employeer = em.find(Funcionario.class, id); 
        
        return employeer;
	}
	
	/** método para criar funcionário. */
	public Funcionario insertOne (Funcionario employeer) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-employeers");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(employeer);
        em.getTransaction().commit();
        em.close();
        
        return this.findByName(employeer.getName());
	}
	
	/** método para achar um funcionário no banco. */
    public Funcionario findByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-employeers");
        EntityManager em = emf.createEntityManager();
		    
        TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f WHERE f.name = :name", Funcionario.class);
        query.setParameter("name", name);
        Funcionario funcName = query.getSingleResult();
        em.close();
		    
        return funcName;
    }
	  
		
    /** método para deletar funcionário. */
    public List<Funcionario> deletePerson (String name) {
		    
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-employeers");
        EntityManager em = emf.createEntityManager();

        Funcionario entityToDelete = em.find(Funcionario.class, this.findByName(name).getId());

        em.getTransaction().begin();
        em.remove(entityToDelete);
        em.getTransaction().commit();
		    
        em.close();
		    
        return this.findAll();
		    
    }
    
    /** método para retornar todos os funcionários. */
	public List<Funcionario> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-employeers");
		EntityManager em = emf.createEntityManager();
		
		@SuppressWarnings("unchecked")
		List<Funcionario> allFunc = em.createQuery("FROM Funcionario").getResultList();
		em.close();
		
		return allFunc;
	}	  
		  
    /** método para atualizar o salario de um funcionário. */
    public Funcionario updateRemuneration(Funcionario employeer, BigDecimal value) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-employeers");
        EntityManager em = emf.createEntityManager();
		    
        employeer.setRemuneration(value);
		    
        em.getTransaction().begin();
        em.merge(employeer);
        em.getTransaction().commit();
		    
        return this.findByName(employeer.getName());
    }
}
