package com.example.minor.repositories;

import com.example.minor.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    public Author findByEmail(String email);

    List<Author> findByAgeGreaterThanEqualAndCountryAndNameStartingWith(int age, String country, String prefix);

    @Query(value = "select a from author a where a.email = ?1", nativeQuery = true)
    public Author getAuthorGivenEmailIdNativeQuery(String myemail);

    @Query("select a from Author a where a.email = ?1")
    public Author getAuthorGivenEmailIdJPQL(String myemail);

    @Query(value = "select a from authors a where a.land = ?1", nativeQuery = true)
    public List<Author> getAuthorsByCountry(String country);

    @Query(value = "select a from Author a where a.country = ?1")
    public List<Author> getAuthorsByCountryJPQL(String country);
}
