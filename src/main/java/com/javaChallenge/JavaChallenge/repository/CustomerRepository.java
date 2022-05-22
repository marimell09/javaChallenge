package com.javaChallenge.JavaChallenge.repository;

import com.javaChallenge.JavaChallenge.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Customer findByUsername(String username);

    @Query("SELECT c from Customer c JOIN FETCH c.roles where c.username = :username")
    Customer findByUsernameFetchRoles(@Param("username") String username);


}
