package com.final_project.eduflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.final_project.services",
 "com.final_project.DataAccess.Repositories",
  "com.final_project.DataAccess.Interfaces", "com.final_project.eduflow"})
public class EduflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduflowApplication.class, args);
	}

}
