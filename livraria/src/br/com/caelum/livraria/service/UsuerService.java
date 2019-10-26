package br.com.caelum.livraria.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@Stateless
public class UsuerService {
	
	@Inject
	private UsuarioDao dao;

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public Usuario findByLoginWithCriteria(String login) {
		return dao.findByLoginWithCriteria(login);
	}

}
