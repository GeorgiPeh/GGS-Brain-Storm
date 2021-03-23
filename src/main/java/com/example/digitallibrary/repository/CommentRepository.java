package com.example.digitallibrary.repository;

import com.example.digitallibrary.model.Book;
import com.example.digitallibrary.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "comments", path = "comments")
public interface CommentRepository extends JpaRepository<Comment, Integer>
{
  List<Comment> findAllByBook(Book book);
  long deleteByBook(Book book);
}
