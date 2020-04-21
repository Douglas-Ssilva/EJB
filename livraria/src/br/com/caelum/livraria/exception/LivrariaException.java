package br.com.caelum.livraria.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true) //Só isso basta
public class LivrariaException extends RuntimeException{ //assim nao obrigo o programador a fazer o tratamento da exception

	
	private static final long serialVersionUID = 1L;

	public LivrariaException(String msg) {
		super(msg);
	}
	
}
