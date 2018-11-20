package config;

import beans.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.text.DateFormat;
import java.util.Date;

@Configuration
@PropertySource("classpath:client.properties")
public class AppConfig {

    @Autowired
    public Environment environment;

    @Bean
    public DateFormat dateFormat(){
        return DateFormat.getDateTimeInstance();
    }
    @Bean
    @Scope("prototype")
    public Date newDate(){
        return new Date();
    }

    @Bean
    public Client client(){
        Client client = new Client();
        client.setId(environment.getRequiredProperty("id"));
        client.setFullName(environment.getRequiredProperty("name"));
        client.setGreeting(environment.getProperty("greeting"));
        return client;
    }
}
