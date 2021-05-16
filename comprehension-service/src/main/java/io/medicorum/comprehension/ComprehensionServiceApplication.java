package io.medicorum.comprehension;

import com.amazonaws.services.comprehendmedical.AWSComprehendMedical;
import com.amazonaws.services.comprehendmedical.model.DetectEntitiesV2Request;
import com.amazonaws.services.comprehendmedical.model.DetectEntitiesV2Result;
import io.medicorum.comprehension.configuration.AwsComprehendMedicalClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComprehensionServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ComprehensionServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		AWSComprehendMedical client = AwsComprehendMedicalClient.getClient();


		DetectEntitiesV2Request request = new DetectEntitiesV2Request();
		request.setText("Hey, my name is Oppenheimer, I am 57 years old, I suffer from severe tonsilis, " +
				"my doctor prescribed me paracetamol and codeine and I have a tonsils removal surgery " +
				"due 25th june, it itches there when I drink hot liquids");

		DetectEntitiesV2Result result = client.detectEntitiesV2(request);
		result.getEntities().forEach(System.out::println);
		result.getUnmappedAttributes().forEach(System.out::println);
	}

}
