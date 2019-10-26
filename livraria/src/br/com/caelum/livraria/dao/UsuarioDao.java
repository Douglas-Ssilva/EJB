package br.com.caelum.livraria.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.caelum.livraria.modelo.Usuario;

@Stateless
public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;

	public Usuario buscaPeloLogin(String login) {
		TypedQuery<Usuario> createQuery = manager.createQuery("SELECT l FROM Usuario l where l.login= :pLogin", Usuario.class);
		return createQuery.setParameter("pLogin", login).getSingleResult();
	}
	
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public Usuario findByLoginWithCriteria(String login) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> createQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = createQuery.from(Usuario.class);
		Predicate pp= criteriaBuilder.equal(root.get("login"), login);
		createQuery.select(root).where(pp);
		return manager.createQuery(createQuery).getSingleResult();
	}
	
}
