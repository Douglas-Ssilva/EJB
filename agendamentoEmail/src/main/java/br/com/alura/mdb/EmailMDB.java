package br.com.alura.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import br.com.alura.business.AgendamentoEmailBusiness;
import br.com.alura.interceptor.Logger;
import br.com.alura.model.AgendamentoEmail;

@Logger
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/EmailQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class EmailMDB implements MessageListener {

	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;

	@Override
	public void onMessage(Message message) {
		System.out.println("Dentro do onMessage");
		try {
			agendamentoEmailBusiness.enviarEmail(message.getBody(AgendamentoEmail.class));
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
		
	}

}
