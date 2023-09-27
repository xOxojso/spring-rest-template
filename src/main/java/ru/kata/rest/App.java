package ru.kata.rest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kata.rest.config.MyConfig;
import ru.kata.rest.entity.User;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = applicationContext.getBean("communication", Communication.class);
        communication.getAll();

        User user = new User(3L, "James", "Brown", (byte) 30);
        communication.add(user);
        communication.update();
        communication.delete(3L);
    }
}
