package br.com.caelum.livraria.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Livro;

@Stateless
public class LivroService {
	
	@Inject
	private LivroDao dao;

	public void salva(Livro livro) {
		dao.salva(livro);
	}

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<Livro> todosLivros() {
		return dao.todosLivros();
	}
	

}
