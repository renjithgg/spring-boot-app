package com.example.demo.repository;

import com.example.demo.model.Question;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface QuestionRepository extends CrudRepository<Question, Integer> {

}
