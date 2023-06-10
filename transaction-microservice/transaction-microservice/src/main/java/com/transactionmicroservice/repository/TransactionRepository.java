package com.transactionmicroservice.repository;


import com.transactionmicroservice.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository  extends CrudRepository<Transaction, Integer> {




}
