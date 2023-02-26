package com.example.minor.service;

import com.example.minor.models.Author;
import com.example.minor.models.Book;
import com.example.minor.models.Genre;
import com.example.minor.repositories.AuthorRepository;
import com.example.minor.repositories.BookRepository;
import com.example.minor.request.BookCreateRequest;
import com.example.minor.request.BookFilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookService
{
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public void create(BookCreateRequest bookCreateRequest)
    {
        Book book = bookCreateRequest.to();
        Author author = book.getAuthor();

        //Find if the author with the given email exists in db or not
        //If
        //Author authorFromDB = authorRepository.getAuthorGivenEmailIdJPQL(author.getEmail());
        Author authorFromDB = authorRepository.findByEmail(author.getEmail());

        if(authorFromDB == null)
        {
            authorFromDB = authorRepository.save(author);
        }

        //Author authorFromDB = authorRepository.save(author);
        //author.setId(1);
        //book.setAuthor(author);
        book.setAuthor(authorFromDB);
        bookRepository.save(book);

    }
    public void create(Book book)
    {
        bookRepository.save(book);
    }
    public List<Book> find(BookFilterType bookFilterType, String value)
    {
        switch(bookFilterType)
        {
            case NAME:
                return bookRepository.findByName(value);
            case AUTHOR_NAME:
                return bookRepository.findByAuthor_Name(value);
            case GENRE:
                return bookRepository.findByGenre(Genre.valueOf(value));
            case BOOK_ID:
                return bookRepository.findAllById(Collections.singleton(Integer.parseInt(value)));
            default:
                return new ArrayList<>();
        }
    }
}
