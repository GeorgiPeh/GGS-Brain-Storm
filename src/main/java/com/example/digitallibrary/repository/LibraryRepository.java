package com.example.digitallibrary.repository;

import com.example.digitallibrary.model.Book;
import com.example.digitallibrary.model.Library;
import com.example.digitallibrary.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "library", path = "library")
public interface LibraryRepository extends JpaRepository<Library, Integer>
{
  List<Library> findByReaderOrderByLastReadDesc(Reader reader);
  List<Library> findByReaderAndBook(Reader reader, Book book);
}
