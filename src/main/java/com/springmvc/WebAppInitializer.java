package com.springmvc;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.*;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
         applicationContext.setConfigLocation("com.springmvc");
                container.addListener(new ContextLoaderListener(applicationContext));
                ServletRegistration.Dynamic servlet = container.addServlet("springDispatcherServlet", new DispatcherServlet(applicationContext));
                servlet.setLoadOnStartup(1);
                servlet.addMapping("/");
    }
}
