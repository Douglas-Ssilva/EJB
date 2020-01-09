package br.com.alura.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.ApplicationException;

//2 formas de Transform in SystemExcepion

@ApplicationException(rollback = true)
//public class BusinessException extends RuntimeException implements Serializable{
public class BusinessException extends Exception implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<String> messages;

	public BusinessException() {
		super();
		messages= new ArrayList<>();
	}

	public BusinessException(String msg) {
		super(msg);
		messages= new ArrayList<>();
		getMessages().add(msg);
	}

	public List<String> getMessages() {
		return messages;
	}

	public void addMessages(String message) {
		this.messages.add(message);
	}
}
