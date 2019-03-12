package com.nju171250.njuTeacher;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nju171250.njuTeacher.mapper")
public class NjuTeacherApplication {

	public static void main(String[] args) {
		SpringApplication.run(NjuTeacherApplication.class, args);
	}

}
