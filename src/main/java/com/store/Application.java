package com.store;


import com.store.dao.impl.ItemDAOImpl;
import com.store.dao.impl.OrderDAOImpl;
import com.store.service.impl.ItemServiceImpl;
import com.store.service.impl.OrderServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

    }

}
