package com.example.demo.repository;

import com.example.demo.model.Renjith;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RenjithRepository extends CrudRepository<Renjith, Integer> {

}
