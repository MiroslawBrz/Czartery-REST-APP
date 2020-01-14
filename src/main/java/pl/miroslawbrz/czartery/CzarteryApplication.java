package pl.miroslawbrz.czartery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication

@PropertySource("classpath:message.properties")

public class CzarteryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CzarteryApplication.class, args);
    }

}
