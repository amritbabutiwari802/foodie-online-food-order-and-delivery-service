package com.foodie.gateway.configuration;

import com.foodie.gateway.models.UserAuthority;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final PathValidator pathValidator;
    private final JwtUtils jwtUtils;

    @Value("${foodie.internal.secret}")
    private String internalSecret;

    @Value("${foodie.gateway.principal-added-secret")
    private String principalAddedSecret;

    public AuthenticationFilter(PathValidator pathValidator, JwtUtils jwtUtils) {
        this.pathValidator = pathValidator;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if(pathValidator.isSecured.test(exchange.getRequest())){
                //check header
                ServerHttpRequest request = exchange.getRequest();
                if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                }
                String authorizaton = request.getHeaders().get(org.springframework.http.HttpHeaders.AUTHORIZATION).get(0);
                if(authorizaton==null || !authorizaton.startsWith("Bearer ")){
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
                }
                String token = authorizaton.substring(7);
                if(!this.jwtUtils.validateToken(token)){
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
                }

                String principal = this.jwtUtils.getPrincipalFromJwtToken(token);
                UserAuthority userAuthority = this.jwtUtils.getAuthorityFromJwtToken(token);
                return chain.filter(exchange.mutate().request(exchange.getRequest().mutate()
                        .header("principal", principal)
                        .header("user-authority", userAuthority.toString())
                        .header("internal-secret", internalSecret)
                        .header("principal-added-secret", principalAddedSecret).build()).build());
            }

            return chain.filter(exchange);

        }));
    }



    public static class Config{

    }
}
