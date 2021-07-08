package io.medicorum.discussionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.cors.CorsConfiguration;

@SpringBootApplication
@EnableMongoAuditing
@EnableMongoRepositories("io.medicorum.discussionservice.repositories")
public class DiscussionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscussionServiceApplication.class, args);
	}

}
