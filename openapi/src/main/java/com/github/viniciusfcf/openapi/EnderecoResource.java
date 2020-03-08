package com.github.viniciusfcf.openapi;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name="enderecos", description = "Operações referentes aos endereços cadastrados nos Correios")
public class EnderecoResource {

    @GET
    @Operation(summary = "Busca todos os endereços do mundo todo", 
    	description =  "Descrição mais detalhada utilizando CommonMark syntax, com *Italic*, **Bold**,"
    			+ " ![Image](http://icons.iconarchive.com/icons/ph03nyx/super-mario/256/Mushroom-Super-icon.png)"+
    		"[Link](http://a.com)")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = EnderecoDTO.class)))
    public List<EnderecoDTO> buscarEnderecos() {
        return null;
    }

    

}