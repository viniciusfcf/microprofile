package com.github.viniciusfcf.openapi;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class SalvarPessoaDTO {

    @Schema(description = "Nome completo", maxLength = 100, required = true)
    public String nome;

    @Schema(description = "Idade no calendário gregoriano", minimum = "1", required = true)
    public Integer idade;

    @Schema(description = "Lista de endereços que a pessoa pode receber encomendas.", maxItems = 2)
    public List<EnderecoDTO> enderecos;
}
