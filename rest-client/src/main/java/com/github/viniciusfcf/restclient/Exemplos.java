package com.github.viniciusfcf.restclient;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.net.ssl.HostnameVerifier;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.RestClientDefinitionException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@SuppressWarnings("unused")
public class Exemplos {

	/*
	 * Método utilizando Rest Client programaticamente com Builder
	 * 
	 */
	public List<User> utilizandoBuilderConfig() throws RestClientDefinitionException, URISyntaxException {
		RestClientBuilder builder = RestClientBuilder.newBuilder().baseUri(new URI("http://quarkus.io/api/v1/"));
		UsuarioResourceClient usuarioResource = builder.build(UsuarioResourceClient.class);
		return usuarioResource.getUsers();
	}

	/*
	 * Método utilizando Rest Client programaticamente com CDI
	 * 
	 */
	public List<User> utilizandoBuilderConfigCDI() throws RestClientDefinitionException {
		UsuarioResourceClient client = CDI.current().select(UsuarioResourceClient.class, RestClient.LITERAL).get();
		return client.getUsers();
	}

	/**
	 * quando CDI estiver disponível, podemos injetar
	 */
	@Inject
	@RestClient
	private UsuarioResourceClient usuarioResource;

	
	// Exemplos SSL
	
	public void configTrustStore() {
		KeyStore trustStore = null; //Por padrão, utiliza trust store da JVM
		RestClientBuilder builder = RestClientBuilder.newBuilder().trustStore(trustStore);
	}
	
	public void configKeyStore() {
		KeyStore keyStore = null;
		RestClientBuilder builder = RestClientBuilder.newBuilder().keyStore(keyStore, "changeit");
	}
	
	public void configHostnameVerifier() {
		HostnameVerifier verifier = null;
		RestClientBuilder builder = RestClientBuilder.newBuilder().hostnameVerifier(verifier);
	}
}
