package com.example.minor.request;

import com.example.minor.models.Author;
import com.example.minor.models.Book;
import com.example.minor.models.Genre;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateRequest
{
    @NotBlank
    private String name;

    @NotNull
    private Author author;

    @Positive
    private int cost;

    @NotNull
    private Genre genre;

    public Book to()
    {
        return Book.builder()
                .cost(this.cost)
                .genre(this.genre)
                .name(this.name)
                .author(this.author)
                .build();
    }
}
