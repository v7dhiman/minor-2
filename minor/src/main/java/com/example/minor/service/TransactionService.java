package com.example.minor.service;

import com.example.minor.exceptions.TxnServiceException;
import com.example.minor.models.Book;
import com.example.minor.models.Student;
import com.example.minor.models.Transaction;
import com.example.minor.models.TransactionType;
import com.example.minor.repositories.TransactionRepositry;
import com.example.minor.request.BookFilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService
{
    @Autowired
    StudentService studentService;

    @Autowired
    BookService bookService;

    @Autowired
    TransactionRepositry transactionRepositry;

    @Transactional
    public String issueTxn(int studentId, int bookId) throws TxnServiceException {
        Student student = studentService.findStudentByStudentId(studentId);
        if(student == null)
        {
            throw new TxnServiceException("Student not present in library");
        }
        List<Book> books = bookService.find(BookFilterType.BOOK_ID, String.valueOf(bookId));
        if(books == null || books.size() != 1)
        {
            throw new TxnServiceException("Book not present in the library");
        }
        Book book = books.get(0);
        if(book.getMy_student() != null)
        {
            throw new TxnServiceException("Book is already issued to someone");
        }
        Transaction transaction = Transaction.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUE)
                .payment(books.get(0).getCost())
                .my_book(books.get(0))
                .my_student(student)
                .build();

        transactionRepositry.save(transaction);
        books.get(0).setMy_student(student);
        bookService.create(books.get(0));
        return transaction.getExternalTxnId();
    }
    public void returnTxn()
    {

    }
}