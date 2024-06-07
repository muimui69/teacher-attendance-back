package com.teacherattendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Import;
//
import com.teacherattendance.config.WebConfig;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(WebConfig.class)
public class TeacherAttendanceBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeacherAttendanceBackApplication.class, args);
	}

}


