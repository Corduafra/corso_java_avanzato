package it.cgmconsulting.CORDUA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CorduaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorduaApplication.class, args);
	}

}
