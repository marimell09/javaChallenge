package com.javaChallenge.JavaChallenge.repository;

import com.javaChallenge.JavaChallenge.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
