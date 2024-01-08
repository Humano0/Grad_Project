package com.final_project.eduflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.final_project.services",
 "com.final_project.DataAccess.Repositories",
  "com.final_project.DataAccess.Interfaces", "com.final_project.eduflow"})
/* @EnableJpaRepositories({"com.final_project.DataAccess.Repositories"})
@EntityScan(basePackages = {"com.final_project.datalayer", "com.final_project.DataAccess.Repositories"}) */
public class EduflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduflowApplication.class, args);
	}

}
