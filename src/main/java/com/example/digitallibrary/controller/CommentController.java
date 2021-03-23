package com.example.digitallibrary.controller;

import com.example.digitallibrary.dto.CommentDTO;
import com.example.digitallibrary.dto.CreateCommentDTO;
import com.example.digitallibrary.model.Book;
import com.example.digitallibrary.model.Comment;
import com.example.digitallibrary.model.Reader;
import com.example.digitallibrary.service.BookService;
import com.example.digitallibrary.service.CommentService;
import com.example.digitallibrary.service.ReadersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sun.istack.NotNull;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;


@RestController
@RequestMapping("/api/v1/book/{id}/comments")
public class CommentController
{
  private final  BookService    bookService;
  private final CommentService commentService;
  private final ReadersService readersService;

  public CommentController(BookService bookService, CommentService commentService, ReadersService readersService)
  {
    this.bookService = bookService;
    this.commentService = commentService;
    this.readersService = readersService;
  }

  @PostMapping()
  public ResponseEntity<?> addComment(@PathVariable @NotNull Integer id,
                                         @RequestBody CreateCommentDTO createCommentDTO,
                                         Principal principal)
  {
    Optional<Book> b = bookService.getBook(id);
    if ((!b.isPresent())) {
      return ResponseEntity.notFound().build();
    }
    else {
      Reader reader = readersService.getReader(principal.getName()).get();
      Book book = b.get();
      Comment comment = new Comment();
      comment.setReader(reader);
      comment.setBook(book);
      comment.setDate(LocalDateTime.now());
      comment.setRating(createCommentDTO.getRating());
      comment.setText(createCommentDTO.getContent());
      commentService.addComment(comment);
      bookService.addRating(book, createCommentDTO.getRating());
      return ResponseEntity.ok().build();
    }
  }

  @GetMapping()
  public ResponseEntity<List<CreateCommentDTO>> getAllBookComments(@PathVariable @NotNull Integer id)
  {
    Optional<Book> book = bookService.getBook(id);
    if (!book.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    else {
      Comparator<CreateCommentDTO> comparatorByTime = (a, b) -> b.getTime().compareTo(a.getTime());
      List<CreateCommentDTO> sortedComments = new ArrayList<>();
      List<Comment> commentsFromDb = commentService.getAllComments(book.get());
      for (Comment c : commentsFromDb
      ) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setUserName(c.getReader().getUsername())
            .setId(c.getId())
            .setContent(c.getText())
            .setTime(c.getDate())
            .setRating(c.getRating());
        sortedComments.add(commentDTO);
      }
      Collections.sort(sortedComments, comparatorByTime); // TODO do the sorting in SQL
      return ResponseEntity.ok(sortedComments);
    }
  }


  @DeleteMapping("/{commentId}")
  public ResponseEntity<?> removeComment(@PathVariable @NotNull Integer id, @PathVariable @NotNull Integer commentId)
  {
    Optional<Book> book = bookService.getBook(id);
    if (!book.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    else {
      Optional<Comment> comment = commentService.getComment(commentId);
      if (!comment.isPresent()) {
        return ResponseEntity.notFound().build();
      }
      else {
        commentService.deleteComment(comment.get());
        bookService.removeRating(book.get(), comment.get().getRating());
        return ResponseEntity.ok().build();
      }
    }
  }
}
