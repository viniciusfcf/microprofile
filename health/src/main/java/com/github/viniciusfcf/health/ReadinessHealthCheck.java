package com.github.viniciusfcf.health;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

/*
 * Quarkus já adiciona healthchecks para: banco jdbc, mongodb, kafka e neo4j
 * 
 */
@Readiness
@ApplicationScoped
public class ReadinessHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder builder = HealthCheckResponse.named("Validando algo necessário para estar pronto...");
        if(valideAlgo()) {
        	// se estiver false... só chamar o método DOWN...
        	builder.down();
        }
		return builder.build();
    }

	private boolean valideAlgo() {
		return false;
	}

}
