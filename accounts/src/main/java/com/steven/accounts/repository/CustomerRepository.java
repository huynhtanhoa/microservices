package com.steven.accounts.repository;


import com.steven.accounts.entitiy.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // find by mobile number
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
