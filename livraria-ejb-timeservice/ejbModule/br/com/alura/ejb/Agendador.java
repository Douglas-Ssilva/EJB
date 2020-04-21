package br.com.alura.ejb;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class Agendador {
	
	@Schedule(hour = "*", minute = "*", second = "*/10", persistent = false)//a cada 10 segundos, não persista as informações aqui
	public void chamarServiçoExterno() {
		
		System.out.println("Serviço chamado...");
	}

}
