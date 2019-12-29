package com.github.viniciusfcf.microprofile;

import org.eclipse.microprofile.opentracing.Traced;



/*
 * NÃO é necessário em uma classe JAXRS.
 */
@Traced(operationName = "${HTTP method}:${package name}.${class name}.${method name}", value = true)
public class MinhaClasseRest {

    public String hello() {
    	new MinhaClasseServico().fazAlgoPorAnotacao();
        return "hello";
    }
}