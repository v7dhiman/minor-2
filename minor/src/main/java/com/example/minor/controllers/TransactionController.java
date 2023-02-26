package com.example.minor.controllers;

import com.example.minor.exceptions.TxnServiceException;
import com.example.minor.models.Student;
import com.example.minor.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController
{
    @Autowired
    TransactionService transactionService;

    @PostMapping("transaction/issue")
    public String issueTxn(@RequestParam("studentId") int studentId, @RequestParam("bookId") int bookID) throws TxnServiceException {
        // Student exists or not
        // Book is present and is available
        // Create a transaction --> Saving in the txn DB
        // Make the book unavailable
        return transactionService.issueTxn(studentId, bookID);
    }
    // Only for students
//    @GetMapping("/student")
//    public Student getStudent()
//    {
//
//    }
}