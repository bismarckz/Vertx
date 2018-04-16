package com.javasampleapproach.reactive.mongodb.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.javasampleapproach.reactive.mongodb.model.Customer;

import reactor.core.publisher.Flux;

public interface ReactiveCustomerRepository extends ReactiveCrudRepository<Customer, String> {

	Flux<Customer> findByName(String name);
}
