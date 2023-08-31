package org.cydeo;

import org.cydeo.config.ProjectConfig;
import org.cydeo.model.Comment;
import org.cydeo.service.CommentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CydeoApp {
    public static void main(String[] args) {
        Comment comment = new Comment();
        comment.setAuthor("Jhonson");
        comment.setText("Spring Framework");

        //By default Spring is using "eager instantiation" which means it will create all singleton beans when it initializes the context (after row below).
        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        //@Lazy annotation tell Spring that it needs to create the bean only when
        //someone refers to the bean for the first time.
        CommentService cs1 = context.getBean(CommentService.class);
        CommentService cs2 = context.getBean(CommentService.class);

        System.out.println(cs1);
        System.out.println(cs2);

        /*By default Spring is using Singleton Scope. You will always get the same instance when refer to a specific bean. Sout will return true*/
        /*When using @Scope("prototype") there will be new instance created and will be 2 different objects Sout will return false*/
        System.out.println(cs1 == cs2);


    }
}
