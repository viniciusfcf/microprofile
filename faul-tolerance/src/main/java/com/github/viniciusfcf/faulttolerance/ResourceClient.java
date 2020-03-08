package com.github.viniciusfcf.faulttolerance;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

@ApplicationScoped
public class ResourceClient {

	@Timeout(
			value = 1000,
			unit = ChronoUnit.MILLIS
	)
	public void timeout() {

	}
	
	
	@Retry(
			maxRetries=3,// quantidade máxima de tentativas. -1 = para sempre
			
			delay=0,//atraso entre cada nova tentativa
			delayUnit=ChronoUnit.MILLIS,//unidade do atraso

			maxDuration=180000,//duração máxima de todas as tentivas, atingindo a duração máxima, nenhuma nova tentativa será feita
			durationUnit=ChronoUnit.MILLIS,//unidade da duração

			jitter=200,//a variação aleatória dos atrasos de nova tentativa
			jitterDelayUnit=ChronoUnit.MILLIS,//unidade do jitter

			retryOn= {IndexOutOfBoundsException.class},//Exceções para tentar novamente
			abortOn={NullPointerException.class}//Exceções para abortar os retries
	)
	public void retry() {

	}

	@Fallback(
			value = MeuHandler.class,
			fallbackMethod =  "nomeMetodo", // Ou declara value, ou o fallbackMethod
			
			//#MP-3.3 Criados na versão 3.3 de Microprofile
			applyOn = Throwable.class,
			skipOn = Throwable.class
	)
	public void fallBack() {

	}
	
	@ApplicationScoped
	public class MeuHandler implements FallbackHandler<Void> {

		@Override
		public Void handle(ExecutionContext context) {
			return null;
		}
		
	}
	
	@CircuitBreaker(
			failOn=Throwable.class,//Considera falha se lançar Throwable ou qualquer subclasse
			delay=5000,//delay aplicado depois que o circuito abre
			delayUnit = ChronoUnit.MILLIS,//unidade do delay
			requestVolumeThreshold = 20,//número de requisições consideradas para o failureRatio
			failureRatio = .50,//limite de falha para abrir o circuito. Valor entre 0 e 1. 
							   //Neste exemplo, 10 falhas das últimas 20 requisições, abre o circuito
			successThreshold = 1,//Quantidade de sucessos, depois do delay, para fechar o circuito novamente
			
			//#MP-3.3 Criado na versão 3.3 de Microprofile
			skipOn = Throwable.class
			)
	public void circuitBreaker() {
		
	}
	
	@Bulkhead(
			value = 10, // número máximo de chamadas concorrentes
			waitingTaskQueue = 10 // Número máximo de elementos em fila aguardando
						 	      // Só pode ser utilizado em métodos anotados com @Asynchronous
	)
	public void bulkhead() {
		
	}
	
	@Asynchronous
	public java.util.concurrent.CompletionStage<Integer> asynchronous1() {
		return CompletableFuture.completedFuture(1);
	}
	
	@Asynchronous
	public java.util.concurrent.Future<Integer> asynchronous2() {
		return CompletableFuture.completedFuture(1);
	}
}
