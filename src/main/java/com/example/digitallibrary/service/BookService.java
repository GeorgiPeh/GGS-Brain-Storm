package com.example.digitallibrary.service;

import com.example.digitallibrary.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService
{
  Optional<Book> getBook(Integer id);
  Optional<Book> getBook(String title, String author);
  void addRating(Book book, Integer rating);
  void removeRating(Book book, Integer rating);
  Book addBook(Book book);
  void deleteBook(Book book);

  List<Book> findBookByCriteria(String title, String author);
}
