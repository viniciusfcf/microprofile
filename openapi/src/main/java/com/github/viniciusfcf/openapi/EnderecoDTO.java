package com.github.viniciusfcf.openapi;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Representa um endereço cadastrado nos correios")
public class EnderecoDTO {

    public String rua;

    public String cep;
    
    public Integer numero;
}
