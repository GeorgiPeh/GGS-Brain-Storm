package com.example.digitallibrary.service;

import com.example.digitallibrary.model.Book;
import com.example.digitallibrary.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService
{
  List<Comment> getAllComments(Book book);

  void deleteComments(Book book);

  void addComment(Comment comment);

  Optional<Comment> getComment(Integer commentId);

  void deleteComment(Comment comment);
}
