package org.cydeo;

import org.cydeo.config.AppConfigSalary;
import org.cydeo.service.SalaryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SalaryApp {
    public static void main(String[] args) {
        ApplicationContext container = new AnnotationConfigApplicationContext(AppConfigSalary.class);

        container.getBean(SalaryService.class).calculateRegularSalary();
    }
}
