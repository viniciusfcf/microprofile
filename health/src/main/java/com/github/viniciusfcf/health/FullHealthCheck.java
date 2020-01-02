package com.github.viniciusfcf.health;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

@ApplicationScoped
public class FullHealthCheck {

	@Produces
	@Liveness
	HealthCheck check1() {
		return () -> HealthCheckResponse.named("Memoria").state(usoMemoria() < 0.9).build();
	}

	@Produces
	@Readiness
	HealthCheck check2() {
		return () -> HealthCheckResponse.named("CPU").state(usoCPU() < 0.9).build();
	}

	private double usoMemoria() {
		return 0;
	}

	private double usoCPU() {
		return 0;
	}

}
