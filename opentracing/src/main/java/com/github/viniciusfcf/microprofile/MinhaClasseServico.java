package com.github.viniciusfcf.microprofile;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.opentracing.Span;
import io.opentracing.Tracer;

/*
 * É NECESSÁRIO EM CLASSES INTERNAS, CASO QUEIRA FAZER TRACING. 
 * 
 * Pode ser utilizado em métodos
 */
//@Traced(operationName = "${package name}.${class name}.${method name}", value = true)
@ApplicationScoped
public class MinhaClasseServico {

	@Inject
	private Tracer tracer;
	
	public void fazAlgoPorAnotacao() {
		
	}
	
	public void fazAlgoManualmente() {
		
		//Exemplo de log se um evento/passo que ocorreu no método
		tracer.activeSpan().log("eventSpanName");
		/*
		 * Nota 1: Baggage é propagada apenas para os filhos futuros (recursivos) deste SpanContext.
		 * Nota 2: Baggage é enviado em todas as chamadas locais e remotas subsequentes; portanto, esse recurso deve ser usado com cuidado.
		 * 
		 */
		tracer.activeSpan().setBaggageItem("key", "value");
		tracer.activeSpan().setTag("tagKey", "tagValye");
		
		//não precisa inicializar explicitamente o pai
		Span parent = tracer.scopeManager().activeSpan();
		Span spanFilho = tracer.buildSpan("SpanFilho1").withTag("tagKey1", "tagValue1").asChildOf(parent).start();
		//...
		spanFilho.setOperationName("fazendoAlgo...");
		//Deve ser a última chamada ao span
		spanFilho.finish();
		
	}
	
	

}
