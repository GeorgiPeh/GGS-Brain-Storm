package com.example.digitallibrary.service;

import com.example.digitallibrary.model.Book;
import com.example.digitallibrary.model.Comment;
import com.example.digitallibrary.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService
{

  CommentRepository commentRepository;

  public CommentServiceImpl(CommentRepository commentRepository)
  {
    this.commentRepository = commentRepository;
  }

  @Override
  public List<Comment> getAllComments(Book book)
  {
    return commentRepository.findAllByBook(book);
  }

  @Override
  public void deleteComments(Book book)
  {
    commentRepository.deleteByBook(book);
    commentRepository.flush();
  }

  @Override
  public void addComment(Comment comment)
  {
    commentRepository.saveAndFlush(comment);
  }

  @Override
  public Optional<Comment> getComment(Integer commentId)
  {
    return commentRepository.findById(commentId);
  }

  @Override
  public void deleteComment(Comment comment)
  {
    commentRepository.delete(comment);
    commentRepository.flush();
  }
}
