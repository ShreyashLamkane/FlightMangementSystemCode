package com.flight.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import com.flight.gateway.util.JwtUtil;

import com.google.common.net.HttpHeaders;

import io.jsonwebtoken.Claims;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
	@Autowired
	private RouteValidator validator;

	// @Autowired
//	    private RestTemplate template;
	@Autowired
	private JwtUtil jwtUtil;

	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if (validator.isSecured.test(exchange.getRequest())) {
				// header contains token or not
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("missing authorization header");
				}

				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
				}
				try {
//                    //REST call to AUTH service
//                    template.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
					jwtUtil.validateToken(authHeader);
					Claims claims = jwtUtil.extractClaims(authHeader);
					String role = claims.get("role", String.class);
					logger.info("Role Extracted from header: {}", role);

					if ("admin".equals(role)) {
						// If role is of admin the it should be able to access all microservices

						exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR,
								exchange.getRequest().getURI().resolve("/"));
					}

					else if ("user".equals(role)) {
						String path = exchange.getRequest().getURI().getPath();
						logger.info("String path: {}", path);
						if (path.startsWith("/bookings")) {
							return chain.filter(exchange);
						} else if (path.startsWith("/passengers")) {
							return chain.filter(exchange);
						} else if (path.startsWith("/web")) {
							return chain.filter(exchange);
						} else if (path.startsWith("/payment")) {
							return chain.filter(exchange);
						}
						else {
							throw new RuntimeException();
						}

					}

				} catch (RuntimeException e) {
					System.out.println("invalid access...!");
					throw new RuntimeException("un authorized access to application");
				}
			}
			return chain.filter(exchange);
		});

	}

	public static class Config {

	}
}
