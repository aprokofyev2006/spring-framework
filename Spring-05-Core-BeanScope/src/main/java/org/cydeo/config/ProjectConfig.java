package org.cydeo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = "org.cydeo")
@ComponentScan(basePackages = {"org.cydeo.proxy","org.cydeo.service","org.cydeo.repository"})
public class ProjectConfig {
}
