package pl.wat.mnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = { "pl.wat.mnd" })
public class MNDApplication {

	public static void main(String[] args) {
		SpringApplication.run(MNDApplication.class, args);
	}


}
