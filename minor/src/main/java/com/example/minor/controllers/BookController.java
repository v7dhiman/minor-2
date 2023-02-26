package com.example.minor.controllers;

import com.example.minor.models.Book;
import com.example.minor.request.BookCreateRequest;
import com.example.minor.request.BookFilterType;
import com.example.minor.request.BookSearchOperationType;
import com.example.minor.response.BookSearchResponse;
import com.example.minor.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public void create(@RequestBody @Valid BookCreateRequest bookCreateRequest)
    {
        bookService.create(bookCreateRequest);
    }

    @GetMapping("/books/search")
    public List<BookSearchResponse> findBooks(
            @RequestParam("filter")BookFilterType bookFilterType,
            @RequestParam("value") String value)
    {
        return bookService
                .find(bookFilterType, value)
                .stream()
                .map(book -> book.to())
                .collect(Collectors.toList());
    }
    @GetMapping("/books/search/robust")
    public List<BookSearchResponse> findBooks2(
            @RequestParam("filter")BookFilterType bookFilterType,
            @RequestParam("value") String value,
            @RequestParam("operationType") BookSearchOperationType bookSearchOperationType)
    {
        return bookService
                .find(bookFilterType, value)
                .stream()
                .map(book -> book.to())
                .collect(Collectors.toList());
    }
}
