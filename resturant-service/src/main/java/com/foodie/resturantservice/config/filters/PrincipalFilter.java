package com.foodie.resturantservice.config.filters;

import com.foodie.resturantservice.config.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

@Component
@Order(0)
public class PrincipalFilter extends OncePerRequestFilter {

    @Value("${principal-added-secret}")
    String principalAddedSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String principal= null;
        boolean hasPrincipal= false;
        boolean hasPrincipalAddedSecret = false;
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            if(headerName.equals("principal-added-secret")){
                Enumeration<String> headerValues = request.getHeaders(headerName);
                while (headerValues.hasMoreElements()){
                    String headerValue = headerValues.nextElement();
                    if(! headerValue.equals(principalAddedSecret)){
                        continue;
                    }

                    hasPrincipalAddedSecret=true;
                }
            } else if(headerName.equals(Constants.PRINCIPAL)){
                Enumeration<String> principals = request.getHeaders(headerName);
                principal = principals.nextElement();
                hasPrincipal=true;
            }
        }

        if(hasPrincipalAddedSecret && hasPrincipal){
            request.setAttribute(Constants.PRINCIPAL, UUID.fromString(principal));
            filterChain.doFilter(request,response);
        } else {
            response.sendError(HttpStatus.BAD_REQUEST.value());
        }
    }
}