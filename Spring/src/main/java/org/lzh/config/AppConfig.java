package org.lzh.config;

import org.lzh.MyService;
import org.lzh.MyServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.sql.SQLException;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public HandlerMapping handlerMapping() {
        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
        // Map URL patterns to controller instances
        handlerMapping.setUrlMap(Map.of(
                "/objects", myController()
        ));
        return handlerMapping;
    }

    @Bean
    public HandlerAdapter handlerAdapter() {
        return new RequestMappingHandlerAdapter();
    }

    @Bean
    public MyServlet myController() {
        return new MyServlet();
    }

    @Bean
    public MyService myService() {
        // Provide necessary parameters to initialize MyService, e.g., JDBC URL, username, password
        try {
            return new MyService("jdbc:mysql://localhost:3306/my_database", "username", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
