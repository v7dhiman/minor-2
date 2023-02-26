package com.example.minor.repositories;

import com.example.minor.models.Book;
import com.example.minor.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer>
{
    List<Book> findByName(String name);
    List<Book> findByAuthor_Name(String name);
    List<Book> findByGenre(Genre genre);
}
