package com.github.viniciusfcf.jwt;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.auth.LoginConfig;

// A Anotação LoginConfig fornece as mesmas informações que o elemento web.xml login-config.
// O uso pretendido é marcar um Aplicativo JAX-RS como requerendo MicroProfile JWT RBAC
@LoginConfig(authMethod = "MP-JWT", realmName = "jwt-jaspi")
@ApplicationScoped
@ApplicationPath("/")
public class MyApplication extends Application {
}