package com.example.mongospring;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StrudentRepository extends MongoRepository<Student, String> {
}
