package com.springmvc.utils;

import com.springmvc.entities.Employee;
import com.springmvc.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Component
public final class HibernateUtil {
    private static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

    public HibernateUtil() {
        //throw new IllegalStateException("HibernateUtil is utility class");
    }

    @Value("${spring.datasource.url}")
    private static String jdbcURl;

    @Value("${spring.datasource.driver}")
    private static String dbDriver;

    @Value("${spring.datasource.username}")
    private static String dbUsername;

    @Value("${spring.datasource.password}")
    private static String dbPassword;

    private static SessionFactory sessionFactory;

    @PostConstruct
    private static SessionFactory buildSessionFactory() {
        if (sessionFactory == null) {
            try {
                org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, dbDriver);
                properties.put(Environment.URL, jdbcURl);
                properties.put(Environment.USER, dbUsername);
                properties.put(Environment.PASS, dbPassword);
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        System.out.println(getSessionFactory().toString());
        getSessionFactory().close();
    }
}
