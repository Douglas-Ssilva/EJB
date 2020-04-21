package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.caelum.livraria.modelo.Livro;

@Stateless
public class LivroDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void salva(Livro livro) {
		manager.persist(livro);
	}
	
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<Livro> todosLivros() {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Livro> createQuery = criteriaBuilder.createQuery(Livro.class);
		createQuery.from(Livro.class);
		return manager.createQuery(createQuery).getResultList();
	}

	public List<Livro> buscarLivrosPeloNome(String nomeLivro) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Livro> createQuery = criteriaBuilder.createQuery(Livro.class);
		Root<Livro> root = createQuery.from(Livro.class);
	    Predicate predicate = criteriaBuilder.like(root.get("titulo"), "%"+ nomeLivro +"%");
	    createQuery.select(root).where(predicate);
		return manager.createQuery(createQuery).getResultList();
	}
	
}
