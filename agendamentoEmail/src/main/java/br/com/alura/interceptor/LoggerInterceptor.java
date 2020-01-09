package br.com.alura.interceptor;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;

@Interceptor
@Priority(1)//Pois há outro interceptor que é o de mensagem
@Logger
public class LoggerInterceptor {
	
	@AroundInvoke
	public Object interceptorMethod(InvocationContext context) throws Exception{
		java.util.logging.Logger logger= java.util.logging.Logger.getLogger(context.getTarget().getClass().getName());
		try {
			
			return context.proceed();
			
		} catch (Exception e) {
			if(e.getCause() instanceof ConstraintViolation) {
				logger.info(e.getMessage());
			}else {
				logger.severe(e.getMessage());
			}
			throw e;
		}
	}

}
