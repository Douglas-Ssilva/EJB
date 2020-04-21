package br.com.caelum.livraria.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;

@Stateless
public class AutorService {
	
	@Inject
	private AutorDao autorDao;
	
	//Abrirá uma transação visto que o padrão é required
	public void add(Autor autor) {
		System.out.println("Autor Service");
		autorDao.salva(autor);
//		throw new RuntimeException();
	}

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public List<Autor> todosAutores() {
		return autorDao.todosAutores();
	}

}
