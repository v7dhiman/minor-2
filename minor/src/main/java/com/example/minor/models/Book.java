package com.example.minor.models;

import com.example.minor.response.BookSearchResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int cost;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("bookList")
    private Author author;

    @ManyToOne
    @JoinColumn
    private Student my_student;

    @OneToMany(mappedBy = "my_book")
    private List<Transaction> transactionList;
    //private Student tempColumn;

    public BookSearchResponse to()
    {
        return BookSearchResponse.builder()
                .id(id)
                .name(name)
                .author(author)
                .cost(cost)
                .genre(genre)
                .student(my_student)
                .transactionList(transactionList)
                .build();
    }
}
