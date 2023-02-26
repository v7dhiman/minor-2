package com.example.minor.response;

import com.example.minor.models.Author;
import com.example.minor.models.Genre;
import com.example.minor.models.Student;
import com.example.minor.models.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookSearchResponse
{
    private int id;
    private String name;
    private int cost;
    private Genre genre;

    @JsonIgnoreProperties("bookList")
    private Author author;

    private Student student;

    private List<Transaction> transactionList;

    private Date createdOn;
    private Date updatedOn;
}
