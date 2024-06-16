package com.teacherattendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import com.teacherattendance.config.WebConfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
@Import(WebConfig.class)
public class TeacherAttendanceBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeacherAttendanceBackApplication.class, args);
	}

	public OpenAPI customOpenAPI(){
		return new OpenAPI().
		info( new Info()
				.title("SISTEMA DE REGISTRO DE ASISTENCIA DE DOCENTES")
				.version("0.0.0")
				.description("Aplicacion simple de spring boot 3 con swagger")
				.termsOfService("http://swagger.io/terms/")
				.license(new License().name("Apache 2.0").url("http://springdoc.org"))

		);
	}

}


