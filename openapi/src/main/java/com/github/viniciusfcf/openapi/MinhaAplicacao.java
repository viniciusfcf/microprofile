package com.github.viniciusfcf.openapi;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(title = "Aprendendo OpenAPI", 
        version = "0.0.1", 
        description = "Exemplo de utilização das principais anotações do OpenAPI",
        contact = @Contact(
            name = "Vinícius Ferraz", 
            email = "vinicius.ferraz@gmail.com",
            url = "http://www.youtube.com/viniciusferraz"
            ),
        license = @License(name = "Minha License", url = "http://endereco-da-licenca.com"),
        termsOfService = "http://endereco-dos-termos.com"
        ),
    servers = {@Server(url = "http://localhost:8080"), @Server(url = "http://localhost-tambem:8080")}
)
public class MinhaAplicacao extends Application {

   
}