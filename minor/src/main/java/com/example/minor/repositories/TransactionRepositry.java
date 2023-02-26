package com.example.minor.repositories;

import com.example.minor.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepositry extends JpaRepository<Transaction, Integer>
{

}
