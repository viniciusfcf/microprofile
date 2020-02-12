package com.github.viniciusfcf.restclient;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;

@Path("/somePath")
public interface MyClient {

    @POST
    @ClientHeaderParam(name="X-Http-Method-Override", value="PUT")
    Response sentPUTviaPOST(User entity);

    @POST
    @ClientHeaderParam(name="X-Request-ID", value="{generateRequestId}")
    Response postWithRequestId(User entity);

    @GET
    @ClientHeaderParam(name="CustomHeader",
                       value="{com.github.viniciusfcf.restclient.generateCustomHeader}",
                       required=false)
    Response getWithoutCustomHeader();

    default String generateRequestId() {
        return UUID.randomUUID().toString();
    }
}

