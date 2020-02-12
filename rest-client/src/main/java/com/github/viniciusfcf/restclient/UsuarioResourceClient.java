package com.github.viniciusfcf.restclient;

import java.util.List;
import java.util.concurrent.CompletionStage;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.ext.DefaultClientHeadersFactoryImpl;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/usuarios")
@RegisterRestClient

// RegisterRestClient se for utilizar CDI
//@RegisterRestClient(baseUri="http://github.com/viniciusfcf/servicos-publicos")
//@RegisterRestClient(configKey = "servicosReceitaFederal")
// Factory responsável por preencher Headers que serão propagados.
//DefaultClientHeadersFactoryImpl propaga os headers da propriedade org.eclipse.microprofile.rest.client.propagateHeaders
@RegisterClientHeaders(DefaultClientHeadersFactoryImpl.class)
public interface UsuarioResourceClient {
	
    @GET
    List<User> getUsers();

    //Async, nao blocante
    @POST
    CompletionStage<Response> createUser(@HeaderParam("Authorization") String authorization,
                                                      User user);
    @PUT
    @Path("/{userId}")
    Response updateUser(@BeanParam PutUser putUser, User user);

    
}

class PutUser {
    @HeaderParam("Authorization")
    private String authorization;
    @PathParam("id")
    private String id;
    // getters, setters, constructors omitted
}


