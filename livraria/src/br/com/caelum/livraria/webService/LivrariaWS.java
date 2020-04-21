package br.com.caelum.livraria.webService;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Livro;

@WebService
public class LivrariaWS {
	
	@Inject
	private LivroDao dao;
	
	@WebMethod(operationName = "buscarLivro")
	@WebResult(name = "livros")
	public List<Livro> buscarLivrosPeloNome(@WebParam(name = "nomeLivro") String nomeLivro) {
		System.out.println(nomeLivro);
		return dao.buscarLivrosPeloNome(nomeLivro);
	}

}
