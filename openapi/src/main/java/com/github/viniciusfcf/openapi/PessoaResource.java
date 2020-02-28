package com.github.viniciusfcf.openapi;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;


@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name="pessoas", description = "Operações públicas referente à todas as pessoas cadastradas.")
public class PessoaResource {

    @GET
    @Operation(summary = "Busta todas as pessoas cadastradas nos sistemas. Dados sigilosos não são retornados")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = PessoaDTO.class)))
    public String buscarPessoas() {
        return "hello";
    }

    @POST
    @Operation(summary = "Cadastra uma pessoa no nosso super sistema")
    public String salvarPessoa(@RequestBody SalvarPessoaDTO dto) {
        return "hello";
    }

    @Path("{id}")
    @DELETE
    @Operation(summary = "Remove logicamente uma Pessoa")
    @APIResponse(responseCode = "404", description = "Caso não exista uma pessoa com o id informado")
    public String apagarPessoa(@PathParam("id") Integer id) {
        return "hello";
    }
    
    @Path("{id}/enderecos")
    @GET
    @Tags({@Tag(name="enderecos"),@Tag(name="pessoas")})
    @Operation(summary = "Busca os endereços de uma única Pessoa")
    @APIResponse(responseCode = "200", content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = EnderecoDTO.class)))
    @APIResponse(responseCode = "404", description = "Caso não exista uma pessoa com o id informado")
    @Parameter(name = "id", description = "Identificador da pessoa",in = ParameterIn.PATH)
    public List<EnderecoDTO> buscarEnderecos(@PathParam("id") Integer id) {
        return null;
    }

}