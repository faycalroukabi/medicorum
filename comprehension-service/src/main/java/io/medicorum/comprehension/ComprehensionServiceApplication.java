package io.medicorum.comprehension;

import com.amazonaws.services.comprehendmedical.AWSComprehendMedical;
import com.amazonaws.services.comprehendmedical.model.DetectEntitiesV2Request;
import com.amazonaws.services.comprehendmedical.model.DetectEntitiesV2Result;
import io.medicorum.comprehension.configuration.AwsComprehendMedicalClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComprehensionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComprehensionServiceApplication.class, args);
	}

}
