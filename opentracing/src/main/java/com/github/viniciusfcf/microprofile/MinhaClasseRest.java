package com.github.viniciusfcf.microprofile;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.opentracing.Traced;



/*
 * NÃO é necessário a anotação @Traced em uma classe JAXRS.
 */
@Traced(operationName = "${HTTP method}:${package name}.${class name}.${method name}", value = true)
@Path("/endpoint")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MinhaClasseRest {

	@Inject
	private MinhaClasseServico servico;
	
	@GET
    public String hello() {
		servico.fazAlgoPorAnotacao();
        return "hello";
    }
}