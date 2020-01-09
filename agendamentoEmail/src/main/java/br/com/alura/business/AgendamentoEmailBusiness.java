package br.com.alura.business;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import br.com.alura.dao.AgendamentoEmailDAO;
import br.com.alura.exception.BusinessException;
import br.com.alura.model.AgendamentoEmail;

@Stateless
public class AgendamentoEmailBusiness {
	
	@Inject
	private AgendamentoEmailDAO agendamentoEmailDAO;
	@Resource(lookup = "java:jboss/mail/AgendamentoMailSession") // Esta anotação faz o lookup de recursos disponibilizados pelo servidor de aplicação.
	private Session session;
	
	private static final String EMAIL_FROM = "mail.address";
	private static final String EMAIL_USER = "mail.smtp.user";
	private static final String EMAIL_PASSWORD = "mail.smtp.pass";
	
	public List<AgendamentoEmail> teste(){
		return agendamentoEmailDAO.getAll();
	}
	
	public void save(@Valid AgendamentoEmail agendamentoEmail) throws BusinessException { //annotation do bean validation
		//regras de negocio separadas da camada DAO
		AgendamentoEmail agendamentoEmail2 = agendamentoEmailDAO.verificarEmailJaExiste(agendamentoEmail.getEmail());
		if(agendamentoEmail2 != null) {
			throw new BusinessException("Email existente!");
		}
		agendamentoEmail.setFoiEnviado(false);
		agendamentoEmailDAO.save(agendamentoEmail);
	}
	
	public List<AgendamentoEmail> retornarEmailsAgendadosNaoEnviados(){
		return agendamentoEmailDAO.retornarEmailsAgendadosNaoEnviados();
	}
	
	public void enviarEmail(AgendamentoEmail agendamento) {
		try {
			System.out.println(session.getProperty(EMAIL_FROM));
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(session.getProperty(EMAIL_FROM));
			mimeMessage.setRecipients(Message.RecipientType.TO, agendamento.getEmail());
			mimeMessage.setSubject(agendamento.getAssunto());
			mimeMessage.setText(Optional.ofNullable(agendamento.getMensagem()).orElse(""));
			Transport.send(mimeMessage, session.getProperty(EMAIL_USER), session.getProperty(EMAIL_PASSWORD));
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void atualizarSituacao(AgendamentoEmail agendamentoEmail) {
		agendamentoEmailDAO.atualizarSituacaoEmail(agendamentoEmail);
	}
	


}
