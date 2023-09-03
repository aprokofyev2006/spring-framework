package bean_practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfigTwo {

    @Bean(name = "s1")
    //@Primary //will return only method with @Primary annotation when we have more than one method for the same class
    public String stringOne(){
        return "Welcome to CydeoApp";
    }

    @Bean
    public String stringTwo(){
        return "Spring Core Practice";
    }
}
