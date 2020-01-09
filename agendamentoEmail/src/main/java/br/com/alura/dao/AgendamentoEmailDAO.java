package br.com.alura.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.model.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<AgendamentoEmail> getAll(){
		return entityManager.createQuery("SELECT a FROM AgendamentoEmail a", AgendamentoEmail.class).getResultList();
	}
	
	public void save(AgendamentoEmail agendamentoEmail) {
		entityManager.persist(agendamentoEmail);
	}
	
	public AgendamentoEmail verificarEmailJaExiste(String email) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AgendamentoEmail> createQuery = criteriaBuilder.createQuery(AgendamentoEmail.class);
		Root<AgendamentoEmail> root = createQuery.from(AgendamentoEmail.class);
		List<Predicate> predicates= new ArrayList<>();
		predicates.add(criteriaBuilder.like(root.get("email"), "%" + email + "%"));
		predicates.add(criteriaBuilder.equal(root.get("foiEnviado"), false));
		createQuery.where((Predicate[]) predicates.toArray(new Predicate[0]));
	    return entityManager.createQuery(createQuery).getResultList().stream().findFirst().orElse(null);
	} 
	
	public List<AgendamentoEmail> retornarEmailsAgendadosNaoEnviados(){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AgendamentoEmail> createQuery = criteriaBuilder.createQuery(AgendamentoEmail.class);
		Root<AgendamentoEmail> root = createQuery.from(AgendamentoEmail.class);
		Path<Boolean> foiEnviado = root.<Boolean>get("foiEnviado");
		Predicate predicate = criteriaBuilder.equal(foiEnviado, false);
		createQuery.where(predicate);
		return entityManager.createQuery(createQuery).getResultList();
	}

	public void atualizarSituacaoEmail(AgendamentoEmail agendamentoEmail) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<AgendamentoEmail> createCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(AgendamentoEmail.class);
		Root<AgendamentoEmail> root = createCriteriaUpdate.from(AgendamentoEmail.class);
		createCriteriaUpdate.set(root.get("foiEnviado"), true);
		createCriteriaUpdate.where(criteriaBuilder.equal(root.get("id"), agendamentoEmail.getId()));
		entityManager.createQuery(createCriteriaUpdate).executeUpdate();
	}
}
























