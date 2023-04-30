package employeer.service;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import employeer.model.Funcionario;

public class EmployeerService {
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
		  
		  public Funcionario findByName(String name) {
		    EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-employeers");
		    EntityManager em = emf.createEntityManager();
		    
		    TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f WHERE f.name = :name", Funcionario.class);
		    query.setParameter("name", name);
		    Funcionario funcName = query.getSingleResult();
		    em.close();
		    
		    return funcName;
		  }
		  
		  public List<Funcionario> findAll() {
		    EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-employeers");
		    EntityManager em = emf.createEntityManager();
		    
		    @SuppressWarnings("unchecked")
		    List<Funcionario> allFunc = em.createQuery("FROM Funcionario").getResultList();
		    em.close();
		    
		    return allFunc;
		  }
		  
		  public List<Funcionario> updateRemuneration(Funcionario employeer, BigDecimal value) {
		    EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-employeers");
		    EntityManager em = emf.createEntityManager();
		    
		    employeer.setRemuneration(value);
		    
		    em.getTransaction().begin();
		    em.merge(employeer);
		    em.getTransaction().commit();
		    
		    return this.findAll();
		  }
		}
