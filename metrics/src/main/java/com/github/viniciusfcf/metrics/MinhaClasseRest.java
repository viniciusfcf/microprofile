package com.github.viniciusfcf.metrics;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricType;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.Tag;
import org.eclipse.microprofile.metrics.annotation.ConcurrentGauge;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.eclipse.microprofile.metrics.annotation.RegistryType;
import org.eclipse.microprofile.metrics.annotation.Timed;

/**
 * 
 * Essas anotações NÃO precisam estar em uma classe Rest...
 * 
 * @author vinicius
 *
 */
@Path("/endpoint")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MinhaClasseRest {

	
	/*
	 * Armazena as métricas criadas e seus metadados baseado nos atributos name e absolute
	 * São 3 tipos de métricas:
	 * 1. application: criados pelo programador
	 * 2. base: definidas na especificação de microprofile
	 * 3. vendor:  específicas do fornecedor (implementação de microprofile) utilizado
	 */
	@Inject
	private MetricRegistry applicationRegistry;
	
	@Inject
	@RegistryType(type=MetricRegistry.Type.BASE)
	private MetricRegistry baseRegistry;
	
	/*
	 * Nenhum dos atributos é obrigatório
	 * Pode ser utilizado em: Método, Construtor e Classe
	 * absolute se refere ao name. Se for true, NÃO inclui o pacote e nome da classe no name 
	 * 
	 * Conta a quantidade de chamadas
	 * 
	 * 
	 */
	
	@Counted(absolute = false, description = "Descrição desse contador", 
			displayName = "Nome que será exibido", name = "Nome do contador", reusable = false, tags = {
			"key=value" }, unit = MetricUnits.NONE)
	/*
	 * Nenhum dos atributos é obrigatório
	 * Pode ser utilizado em: Método, Construtor e Classe 
	 * 
	 * Indica um medidor que conta as invocações paralelas do objeto anotado.
	 */
	@ConcurrentGauge(absolute = false, description = "Descrição desse @ConcurrentGauge", 
			displayName = "Nome que será exibido", name = "Nome do @ConcurrentGauge", reusable = false, tags = {
			"key=value" }, unit = MetricUnits.NONE)

	/*
	 * Somente unit é obrigatório
	 * 
	 * Pode ser utilizado em: Método
	 * 
	 * Indica um medidor, que mostra o valor do objeto anotado.
	 * 
	 * Utilizar apenas em @ApplicationScoped and @Singleton
	 */
	@Gauge(absolute = false, description = "Descrição desse @Gauge", 
			displayName = "Nome que será exibido", name = "Nome do @Gauge", unit = MetricUnits.BYTES)
	
	/*
	 * Nenhum dos atributos é obrigatório
	 * Pode ser utilizado em: Método, Construtor e Classe 
	 * 
	 * Indica um medidor, que rastreia a frequência de invocações do objeto anotado.
	 */
	@Metered(absolute = false, description = "Descrição desse @Metered", 
			displayName = "Nome que será exibido", name = "Nome do @Metered", reusable = false, tags = {
			"key=value" }, unit = MetricUnits.PER_SECOND)
	

	/*
	 * Nenhum dos atributos é obrigatório
	 * Pode ser utilizado em: Método, Construtor e Classe 
	 * 
	 * Indica um medidor, que rastreia a frequência de invocações do objeto anotado.
	 */
	@Timed(absolute = false, description = "Descrição desse @Timed", 
			displayName = "Nome que será exibido", name = "Nome do @Timed", reusable = false, tags = {
			"key=value" }, unit = MetricUnits.NANOSECONDS)

	/*
	 * Nenhum dos atributos é obrigatório
	 * Pode ser utilizado em: Método, Atributo da classe e Parâmetro 
	 * 
	 * Injeta uma metrics, normalmente cria a métrica caso não exista, olhar exemplos abaixo
	 */
	@Metric(absolute = false, description = "Descrição desse @Metric", 
			displayName = "Nome que será exibido", name = "Nome do @Metric", tags = {
			"key=value" }, unit = MetricUnits.NONE)
	
	@GET
	public String hello() {
		//Criando uma métrica manualmente
		Metadata m = Metadata.builder()
				.withName("myCounter")
				.withDescription("Example conter")
				.withType(MetricType.COUNTER)
				.build();

		Counter conter = null;
		applicationRegistry.register(m, conter, new Tag("colour","blue"));
		return "hello";
	}
	
	
	/*
	 * 
	 * Exemplos de @Metric
	 * 
	 * 
	 */
	
	private double hits;
	private double total;

	/*
	 * Registra, se ja existir com o mesmo nome, lançará exceção
	 * Atributos do tipo Meter, Timer, Counter e Histogram.
	 */
	@Inject
	@Metric(name = "applicationCount")
	private Counter count;
	
	/*
	 * Registra, mesmo existindo com mesmo nome
	 */
	@Metric(name="hitPercentage")
	@ApplicationScoped
	private org.eclipse.microprofile.metrics.Gauge<Double> hitPercentage = new org.eclipse.microprofile.metrics.Gauge<Double>() {

	@Override
	  public Double getValue() {
	      return hits / total;
	  }
	};
	
	/*
	 * Se ja existir a métrica, injeta aqui, se não existir, cria uma nova.
	 */
	@Inject
	public void init(@Metric(name="instances") Counter instances) {
	    instances.inc();
	}

}