package com.github.viniciusfcf.health;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

/*
 * LIVENESS é para ver se o sistema não é zumbi, basicamente. Mas...
 * 
 * NÃO utilize @Liveness a não ser que você entenda as consequencias e o porquê você precisa dele
 * 
 * Fonte: https://srcco.de/posts/kubernetes-liveness-probes-are-dangerous.html#id1
 * 
 * Kubernetes possivelmente irá reiniciar o serviço para ver se funciona
 * 
 */
@Liveness
@ApplicationScoped
public class LivenessHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("Não sou um zumbi.").up().build();
    }

}
