package com.github.viniciusfcf.jwt;

import java.util.Optional;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.json.JsonNumber;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.ClaimValue;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/endpoint")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MinhaClasseRest {

	@Inject
	private JsonWebToken jwt;

	@Inject 
	@Claim(standard = Claims.iat)
	private Long issuedAt;

	@Inject
	@Claim("customClaim")
	private Instance<Optional<JsonNumber>> customClaim;

	@Inject
	@Claim(standard = Claims.sub)
	private ClaimValue<String> subjectClaim;

	@GET
	@DenyAll
	@Path("/deny-all")
	public String denyAll() {
		return "hello";
	}

	@GET
	@PermitAll
	@Path("/permit-all")
	public String permitAll() {
		return "hello";
	}

	@GET
	@RolesAllowed({ "role1", "role2" })
	@Path("/roles")
	public String roles() {
		return "hello";
	}
}