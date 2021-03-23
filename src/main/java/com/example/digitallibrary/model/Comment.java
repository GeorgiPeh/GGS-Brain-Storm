package com.example.digitallibrary.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment
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

  @Column(name = "rating")
  private Integer rating;

  @Column(name = "text")
  private String text;

  @Column(name = "comment_date")
  private LocalDateTime date = LocalDateTime.now();

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

  public Integer getRating()
  {
    return rating;
  }

  public void setRating(Integer rating)
  {
    this.rating = rating;
  }

  public String getText()
  {
    return text;
  }

  public void setText(String text)
  {
    this.text = text;
  }

  public LocalDateTime getDate()
  {
    return date;
  }

  public Comment setDate(LocalDateTime date)
  {
    this.date = date;
    return this;
  }
}
