package com.example.digitallibrary.repository;

import com.example.digitallibrary.model.Book;
import com.example.digitallibrary.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepository extends JpaRepository<Book, Integer>
{
  @Query(value = "SELECT * FROM BOOKS b JOIN AUTHORS a ON b.author_id = a.id WHERE b.title like %:title% AND a.username like %:author%",
      nativeQuery = true)
  List<Book> findByTitleContainingOrAuthorContaining(@Param("title") String title, @Param("author") String author);

  @Query(value = "SELECT * FROM BOOKS b JOIN AUTHORS a ON b.author_id = a.id WHERE b.title=:title AND a.username=:author",
      nativeQuery = true)
  List<Book> findByTitleAndAuthor(@Param("title") String title, @Param("author") String author);

  Optional<List<Book>> findByGenre(Genre genre);
}
