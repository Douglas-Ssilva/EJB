package br.com.caelum.livraria.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
public class LogInterceptor {
	
	@AroundInvoke
	public Object interceptor(InvocationContext context) throws Exception{
		
		long millis = System.currentTimeMillis();
		Object proceed = context.proceed();
		System.out.println("ClassName: " + context.getTarget().getClass().getSimpleName());
		System.out.println("MethodName: " + context.getMethod().getName());
		long millisCurrent = System.currentTimeMillis();
		System.out.println("Tempo gasto: " + (millisCurrent - millis));
		
		return proceed;
	}

}
