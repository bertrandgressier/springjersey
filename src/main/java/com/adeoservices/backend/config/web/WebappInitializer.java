package com.adeoservices.backend.config.web;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebappInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext ctx) throws ServletException {


        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        ConfigurableEnvironment env = rootContext.getEnvironment();

        if (env.getProperty("spring.profiles.active") == null) {
            env.setActiveProfiles("dev");
        }

        rootContext.scan("com.adeoservices.backend.config.spring");
        rootContext.refresh();

        ServletRegistration.Dynamic jersey = ctx.addServlet("jerseyServlet", new SpringServlet());

        jersey.setInitParameter("com.sun.ws.rest.config.property.resourceConfigClass","com.sun.ws.rest.api.core.PackagesResourceConfig");
        jersey.setInitParameter("com.sun.ws.rest.config.property.packages","com.adeoservices.backend.controller");
        jersey.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        jersey.setLoadOnStartup(1);
        jersey.addMapping("/api/*");

        ctx.addListener(new ContextLoaderListener(rootContext));
    }
}