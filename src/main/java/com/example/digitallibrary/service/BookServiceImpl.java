package com.example.digitallibrary.service;

import com.example.digitallibrary.model.Book;
import com.example.digitallibrary.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService
{

  public BookServiceImpl(BookRepository bookRepository)
  {
    this.bookRepository = bookRepository;
  }

  private BookRepository bookRepository;

  @Override
  public Optional<Book> getBook(Integer id)
  {
    return bookRepository.findById(id);
  }

  @Override
  public Optional<Book> getBook(String title, String author)
  {
    List<Book> books = bookRepository.findByTitleAndAuthor(title, author);
    return books.isEmpty() ? Optional.empty() : Optional.of(books.get(0));
  }

  @Override
  public void addRating(Book book, Integer rating)
  {
    book.setRating((book.getRating()*book.getNumOfRatings() + rating) / (double) (book.getNumOfRatings()+1));
    book.setNumOfRatings(book.getNumOfRatings()+1);
    bookRepository.saveAndFlush(book);
  }

  @Override
  public void removeRating(Book book, Integer rating)
  {
    book.setRating((book.getRating()*book.getNumOfRatings() - rating) / (double) (book.getNumOfRatings()-1));
    book.setNumOfRatings(book.getNumOfRatings()-1);
    bookRepository.saveAndFlush(book);
  }

  @Override
  public Book addBook(Book book)
  {
    return bookRepository.saveAndFlush(book);
  }

  @Override
  public void deleteBook(Book book)
  {
    bookRepository.delete(book);
    bookRepository.flush();
  }

  @Override
  public List<Book> findBookByCriteria(String title, String author)
  {
    return bookRepository.findByTitleContainingOrAuthorContaining(title, author);
  }
}
