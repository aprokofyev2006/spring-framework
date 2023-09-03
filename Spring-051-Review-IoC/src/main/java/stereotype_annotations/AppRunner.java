package stereotype_annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import stereotype_annotations.config.AppConfigStereotype;
import stereotype_annotations.model.DataStructure;
import stereotype_annotations.model.DevOps;
import stereotype_annotations.model.Microservice;

public class AppRunner {
    public static void main(String[] args) {
        ApplicationContext container = new AnnotationConfigApplicationContext(AppConfigStereotype.class);

        container.getBean(DataStructure.class).getTotalHours();
        container.getBean(Microservice.class).getTotalHours();
        //container.getBean(DevOps.class).getTotalHours();
    }
}
