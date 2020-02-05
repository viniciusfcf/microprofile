package com.github.viniciusfcf.config;

import java.util.Map;
import java.util.Set;

import org.eclipse.microprofile.config.spi.ConfigSource;

/**
 * Caso queira buscar propriedades de um banco de dados qualquer
 * 
 * 
 * Para registrar, criar o arquivo abaixo e em seu conteúdo colocar o nome da classe completo: 
 * Arquivo:
 * 		/META-INF/services/org.eclipse.microprofile.config.spi.ConfigSource
 * Conteúdo:
 * 		com.github.viniciusfcf.config.ConfigSourceBancoDeDados
 * 
 * @author vinicius
 *
 */
public class ConfigSourceBancoDeDados implements ConfigSource {

	@Override
	public int getOrdinal() {
		return 112;
	}

	@Override
	public Set<String> getPropertyNames() {
		return null;
	}

	@Override
	public Map<String, String> getProperties() {
		return null;
	}

	@Override
	public String getValue(String key) {
		return null;
	}

	@Override
	public String getName() {
		return "nome do meu configSource utilizado para log e análise";
	}
}