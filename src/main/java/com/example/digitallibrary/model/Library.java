package com.example.digitallibrary.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "libraries")
public class Library
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @ManyToOne(targetEntity = Reader.class)
  @JoinColumn(name = "reader_id", referencedColumnName = "id")
  private Reader reader;

  @ManyToOne(targetEntity = Book.class)
  @JoinColumn(name = "book_id", referencedColumnName = "id")
  private Book book;

  @Column(name = "last_read", nullable = false)
  private LocalDateTime lastRead;

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public Reader getReader()
  {
    return reader;
  }

  public void setReader(Reader reader)
  {
    this.reader = reader;
  }

  public Book getBook()
  {
    return book;
  }

  public void setBook(Book book)
  {
    this.book = book;
  }

  public LocalDateTime getLastRead()
  {
    return lastRead;
  }

  public void setLastRead(LocalDateTime lastRead)
  {
    this.lastRead = lastRead;
  }
}
