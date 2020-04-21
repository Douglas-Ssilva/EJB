package br.com.caelum.livraria.test;

import java.rmi.RemoteException;

import br.com.caelum.livraria.webService.LivrariaWS;
import br.com.caelum.livraria.webService.LivrariaWSProxy;
import br.com.caelum.livraria.webService.Livro;

public class TesteSoap {

	public static void main(String[] args) throws RemoteException {
		
		LivrariaWS livrariaWS= new LivrariaWSProxy();//sabe fazer a chamada remota e gerar soap
		Livro[] buscarLivro = livrariaWS.buscarLivro("CSS");
		for (Livro livro : buscarLivro) {
			System.out.println("Nome do autor: " + livro.getAutor().getNome() + " dono do: " + livro.getTitulo());
		}
	}

}
