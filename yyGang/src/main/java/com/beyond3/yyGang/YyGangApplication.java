package com.beyond3.yyGang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.beyond3") // 엔티티 클래스가 포함된 패키지
public class YyGangApplication {
	public static void main(String[] args) {
		SpringApplication.run(YyGangApplication.class, args);
	}
}