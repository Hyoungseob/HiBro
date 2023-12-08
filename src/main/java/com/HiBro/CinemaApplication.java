package com.HiBro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.TimeZone;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);

		LocalDateTime now = LocalDateTime.now();
		System.out.println("현재시간" + now);
	}

	public void init(){
		// timezone 설정
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}
