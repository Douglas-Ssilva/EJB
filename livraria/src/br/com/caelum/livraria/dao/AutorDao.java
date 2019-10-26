package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Autor;

@Stateless
public class AutorDao {

	@PersistenceContext //EJB
	private EntityManager manager;

	@PostConstruct //métodos callback, pois é ligado ao ciclo de vida do sessionBean
	void init() {
		System.out.println("AutorDAO"); //é executado quando precisamos obter os autores somente uma vez
	}
	
//	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) caso ocorra exception, não é desfeito o que é feito aqui por quem chamou
	public void salva(Autor autor) {
		manager.persist(autor);
	}
	
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<Autor> todosAutores() {
		return manager.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		return manager.find(Autor.class, autorId);
	}
	
}
