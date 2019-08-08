package com.springmvc;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebAppInitializer implements WebApplicationInitializer {

    public static final String[] VIEW_LIST = {"/", "/home", "/login","/logout","/employees"};

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        XmlWebApplicationContext applicationContext = new XmlWebApplicationContext();
        applicationContext.setConfigLocation("/WEB-INF/web.xml");
                 context.setConfigLocation("com.springmvc");
                container.addListener(new ContextLoaderListener(context));
                ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(context));
                 dispatcher.setLoadOnStartup(1);
                 dispatcher.addMapping(VIEW_LIST);
    }
}
