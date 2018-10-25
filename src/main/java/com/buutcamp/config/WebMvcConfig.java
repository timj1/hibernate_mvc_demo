package com.buutcamp.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;


@Configuration
@ComponentScan("com.buutcamp")
@EnableWebMvc
@EnableTransactionManagement
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public UrlBasedViewResolver urlBasedViewResolver() {
        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setPrefix("/WEB-INF/views/");
        urlBasedViewResolver.setSuffix(".jsp");
        urlBasedViewResolver.setViewClass(JstlView.class);

        return urlBasedViewResolver;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException {
        //Initialize sessionfactorybean
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        //set sessionfactory data source
        sessionFactory.setDataSource(myDataSource());

        //tell hibernate where to find classes to manage
        sessionFactory.setPackagesToScan("com.buutcamp.entity");

        //set hibernate properties
        sessionFactory.setHibernateProperties(hibernateProperties());

        //return sessionfactory
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(
            SessionFactory sessionFactory) {
                HibernateTransactionManager txManager =
                        new HibernateTransactionManager();

                txManager.setSessionFactory(sessionFactory);
                return txManager;
    }

    DataSource myDataSource() throws SQLException {
        //Create datasource
        //MysqlDataSource dataSource = new MysqlDataSource();
        MysqlDataSource dataSource = new MysqlDataSource();
        //DataSource dataSource1 = new
        //Set connection info
        dataSource.setDatabaseName("hibernate_simple_demo");
        dataSource.setUser("user");
        dataSource.setPassword("user");
        dataSource.setUrl("jdbc:mysql://localhost:3306/hibernate_simple_demo?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");

        //return the datasource
        return dataSource;
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "update");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
            }
        };
    }
}
