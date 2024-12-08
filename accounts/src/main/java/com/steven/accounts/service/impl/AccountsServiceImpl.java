package com.steven.accounts.service.impl;

import com.steven.accounts.constants.AccountsConstants;
import com.steven.accounts.dto.CustomerDto;
import com.steven.accounts.entitiy.Accounts;
import com.steven.accounts.entitiy.Customer;
import com.steven.accounts.exception.CustomerAlreadyExistsException;
import com.steven.accounts.mapper.CustomerMapper;
import com.steven.accounts.repository.AccountsRepository;
import com.steven.accounts.repository.CustomerRepository;
import com.steven.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository
                .findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number "
                + customerDto.getMobileNumber());
        }

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Steven");
        Customer savedCustomer = customerRepository.save(customer);

        // create an account object
        accountsRepository.save(createNewAccount(savedCustomer));
    }





    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccessNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccessNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Steven");

        return newAccount;
    }
}
