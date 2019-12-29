package com.github.viniciusfcf.microprofile;

import io.opentracing.Span;
import io.opentracing.Tracer;

/*
 * É NECESSÁRIO EM CLASSES INTERNAS, CASO QUEIRA FAZER TRACING. 
 * 
 * Pode ser utilizado em métodos
 */
//@Traced(operationName = "${package name}.${class name}.${method name}", value = true)
public class MinhaClasseServico {

	
	
	
	public void fazAlgoPorAnotacao() {
		
	}
	
	@SuppressWarnings("null")
	public void fazAlgoManualmente() {
		// nos frameworks ou servidores  utilizar um @Inject na classe
		Tracer tracer = null;
		
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
		Span spanFilho = tracer.buildSpan("SpanFilho1").withTag("", "").asChildOf(parent).start();
		//...
		spanFilho.setOperationName("fazendoAlgo...");
		//Deve ser a última chamada ao span
		spanFilho.finish();
		
	}
	
	

}
