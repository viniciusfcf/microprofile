package com.github.viniciusfcf.config;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;

@ApplicationScoped
@SuppressWarnings("unused")
public class MinhaClasse {

	/**
	 * Método utilizando config programaticamente
	 */
	public void utilizandoConfig() {
		Config config = ConfigProvider.getConfig();

		String serverUrl = config.getValue("com.github.viniciusfcf.alguma.url", String.class);
		System.out.println(serverUrl);
		
		ConfigSource[] sources = null;
		Converter<?>[] converters = null;
		ConfigBuilder configBuilder = ConfigProviderResolver.instance().getBuilder()
				.addDefaultSources()
				.addDiscoveredConverters()
				.addDiscoveredSources().withSources(sources).withConverters(converters);
		Config config2 = configBuilder.build();
	}

	@Inject
	private Config config;

	// A propriedade abaixo deve existir em algum dos configsources,
	// caso contrário DeploymentException será lançado.
	// A não ser que seja configurado o atributo defaultValue
	@Inject
	@ConfigProperty(name = "viniciusfcf.alguma.url")
	private String algumaUrl;

	// Caso a propriedade abaixo não seja configurada, NÃO será lançado
	// DeploymentException
	@Inject
	@ConfigProperty(name = "viniciusfcf.alguma.port")
	private Optional<Integer> algumaPort;

	//Injeta um provider para a propriedade que retornará o valor de modo Lazy.
	// Resolve problemas de dependência circular
	@Inject
	@ConfigProperty(name = "viniciusfcf.algum.timeout.dinamico", defaultValue = "100")
	private javax.inject.Provider<Long> timeout;
	
	// As propriedades abaixo são valores separados por vírgula ex: ( meusAnimais=cachorro,gato,papagaio)
	@Inject
	@ConfigProperty(name = "meusAnimais")
	private String[] meuArray;
	@Inject
	@ConfigProperty(name = "meusAnimais")
	private List<String> minhaLista;
	@Inject
	@ConfigProperty(name = "meusAnimais")
	private Set<String> meuConjunto;

	// Conversores customizados (org.eclipse.microprofile.config.spi.Converter): devem ter uma prioridade maior que 100
	
	// Conversores podem ser registrados no arquivo /METAINF/services/org.eclipse.microprofile.config.spi.Converter 
	// OU:
	// Adicionados programativamente com ConfigBuilder#withConverters(Converter<?>… converters) 
	// OU
	// A classe do atributo injetado deve atender uma das seguintes condições:
	// 1. Método public static T of(String)
	// 2. Método public static T valueOf(String)
	// 3. Construtor com String de parâmetro
	// 4. Método public static T parse(CharSequence)}
}
