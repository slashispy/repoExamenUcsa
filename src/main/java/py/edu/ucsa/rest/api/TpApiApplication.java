package py.edu.ucsa.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages= {"py.edu.ucsa.rest.api.web.controllers",
		  "py.edu.ucsa.rest.api.core.services",
		  "py.edu.ucsa.rest.api.core.dao"})
@Import(JpaConfiguration.class)
public class TpApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpApiApplication.class, args);
	}
}
