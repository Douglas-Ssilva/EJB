package br.com.alura.timer;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.alura.business.AgendamentoEmailBusiness;
import br.com.alura.model.AgendamentoEmail;

@Singleton //Para que haja sincronismo, quando estiver enviando email, as outras tarefas esperem. Quando um serviço estiver rodando, os outros esperam
public class AgendamentoEmailTimer {
	
	private Logger logger= Logger.getLogger(AgendamentoEmail.class.getName());
	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;
	
	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory") //do proprio server
	private JMSContext context;
	
	@Resource(mappedName = "java:/jms/queue/EmailQueue") //fila que criamos
	private Queue queue;
	
	
//	@Schedule(hour = "*", minute = "0,10,20,30,40,50") De 10 em 10 minutos
	@Schedule(hour = "*", minute = "*/2") 
	public void enviarEmailTimer() {
		logger.info("Enviando email...");
		List<AgendamentoEmail> emailsNaoEnviados = agendamentoEmailBusiness.retornarEmailsAgendadosNaoEnviados();
		if(!emailsNaoEnviados.isEmpty()) {
			System.out.println("Dentro do enviarEmailTimer");
			emailsNaoEnviados.forEach(e -> {
				context.createProducer().send(queue, e);
				agendamentoEmailBusiness.atualizarSituacao(e);
			});
		}
	}

}
