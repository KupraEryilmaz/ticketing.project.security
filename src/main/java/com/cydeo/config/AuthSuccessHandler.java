package com.cydeo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Configuration
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities()); // programa login yaptiktan sonra kimlik dogrulama islemi gerceklesiyor.Her ne zaman kimlik dogrulama islemi yapilirsa.bu method user in role unu yakaliyor.peki neden set kullandik cunku bir user birden fazla role a sahip olabilir yani o sayfaya erisebilen birden fazla role olabilir.  .antMatchers("/task/**").hasAnyRole("EMPLOYEE","ADMIN")  gibi task end point sayfalarina iki role da erisebiliyor.Ama bizim databasemizde bir user in bir role i var. o nedenle bu kisim cok onemli degil.

        if(roles.contains("Admin")){
            response.sendRedirect("/user/create");

        }

        if(roles.contains("Manager")){
            response.sendRedirect("/task/create");

        }

        if(roles.contains("Employee")){
            response.sendRedirect("/task/employee/pending-tasks");


        }
    }
}
