package com.example.digitallibrary.repository;

import com.example.digitallibrary.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "authors", path = "authors")
public interface AuthorRepository extends JpaRepository<Author, Integer>
{
  Optional<Author> findAuthorByUsername(String userName);
}
