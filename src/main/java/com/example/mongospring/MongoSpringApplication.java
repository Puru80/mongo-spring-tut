package com.example.mongospring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MongoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate){
		return args -> {
				Address address = new Address(
						"India",
						"Udaipur",
						"313001");
				Student student = new Student(
						"Gagan",
						"Agarwal",
						"gaganagarwala@gmail.com",
						Gender.FEMALE,
						address,
						List.of("History", "Maths"),
						BigDecimal.TEN,
						LocalDateTime.now());

//			usingMongoTemplateAndQuery(repository, mongoTemplate, student);

			repository.findStudentByEmail(student.getEmail())
					.ifPresentOrElse(s -> System.out.println("Student Alredy Exists"),
							() -> {
						System.out.println("Inserting Student...");
						repository.insert(student);
					});
		};
	}

	private void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(student.getEmail()));

		List<Student> list = mongoTemplate.find(query, Student.class);

		if(list.isEmpty()){
			System.out.println("Inserting Student...");
			repository.insert(student);
		}
		else
			System.out.println("Student Alredy Exists");
	}
}
