package bean_practice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppRunner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class,AppConfigTwo.class);

        FullTimeEmployee fullTimeEmployee = context.getBean(FullTimeEmployee.class);
        PartTimeEmployee partTimeEmployee = context.getBean(PartTimeEmployee.class);

        fullTimeEmployee.createAccount();
        partTimeEmployee.createAccount();

        String s1 = context.getBean("s1", String.class);
        String s2 = context.getBean("stringTwo", String.class);

        System.out.println(s1);
        System.out.println(s2);
    }
}
