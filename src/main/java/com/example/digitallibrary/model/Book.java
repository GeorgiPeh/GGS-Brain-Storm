package com.example.digitallibrary.model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @Column(name = "title", nullable = false)
  private String title;

  @ManyToOne(targetEntity = Author.class)
  @JoinColumn(name = "author_id", referencedColumnName = "id")
  private Author author;

  @Column(name = "rating", nullable = false)
  private Double rating = 0.0;

  @Column(name = "number_of_ratings", nullable = false)
  private Integer numOfRatings = 0;

  @Column(name = "number_of_libraries", nullable = false)
  private Integer numOfLibraries = 0;

  @Column(name = "genre", nullable = false)
  private Genre genre;

  @Column(name = "year", nullable = false)
  private Integer year;

  @Column(name = "book_file", nullable = false)
  private byte[] bookFile;

  public byte[] getBookFile()
  {
    return bookFile;
  }

  public void setBookFile(byte[] bookFile)
  {
    this.bookFile = bookFile;
  }

  public Integer getYear()
  {
    return year;
  }

  public void setYear(Integer year)
  {
    this.year = year;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public Author getAuthor()
  {
    return author;
  }

  public void setAuthor(Author author)
  {
    this.author = author;
  }

  public Double getRating()
  {
    return rating;
  }

  public void setRating(Double rating)
  {
    this.rating = rating;
  }

  public Integer getNumOfRatings()
  {
    return numOfRatings;
  }

  public void setNumOfRatings(Integer numOfRatings)
  {
    this.numOfRatings = numOfRatings;
  }

  public Integer getNumOfLibraries()
  {
    return numOfLibraries;
  }

  public void setNumOfLibraries(Integer numOfLibraries)
  {
    this.numOfLibraries = numOfLibraries;
  }

  public Genre getGenre()
  {
    return genre;
  }

  public void setGenre(Genre genre)
  {
    this.genre = genre;
  }
}
