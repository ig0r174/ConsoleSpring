package com.example.consolespring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.io.FileNotFoundException;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class ConsoleSpringApplication implements CommandLineRunner {

    @Autowired
    FileMaster fileMaster;

    public static void main(String[] args) {
        SpringApplication.run(ConsoleSpringApplication.class, args);
    }

    @Override
    public void run(String... args) {

        if (args.length != 1) {
            System.out.println("You need to pass one argument to start application");
        } else {
            try {
                fileMaster.use(args[0]);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }

    }

}
