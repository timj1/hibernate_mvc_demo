package com.buutcamp.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebMvcInit implements WebApplicationInitializer {

    //implementing method so that WebApplicationInitializer requirement is fullfilled
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        //combining this class with the annotated configuration class we created previously
        rootContext.register(WebMvcConfig.class);

        //initializing the servlet
        ServletRegistration.Dynamic registration = servletContext.addServlet("hibernate_mvc_demo",
                new DispatcherServlet(rootContext));
        registration.addMapping("/");
        registration.setLoadOnStartup(1);

    }
    //Create controller to server .jsp pages
    //create a homepage.jsp page to load / show
}
