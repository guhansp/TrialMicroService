package com.bytes.loan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title="Trial Microservice Application API Documentation",
				description = "Aint no way im learning this shit",
				version = "v69",
				contact = @Contact
						(
								name="Guhan",
								email = "guhan@nahug.com",
								url = "guhan.com"
						),
				license = @License
						(
								name = "cant afford",
								url = "bruh"

						)
		)
)
public class LoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}

}
