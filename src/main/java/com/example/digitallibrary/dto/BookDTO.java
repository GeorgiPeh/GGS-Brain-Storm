package com.example.digitallibrary.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookDTO extends CreateBookDTO
{

  @NotNull(message = "Id cannot be NULL!")
  private Integer id;

  @NotNull(message = "Provide author's name!")
  @Size(min = 1, max = 40, message = "The author's name - between 1 and 40 characters!")
  private String  author;

  private Integer countComments;

  @Range(min = 1, max = 10)
  private Double  rating;

  private Integer numberOfLibraries;

  public Integer getNumberOfLibraries()
  {
    return numberOfLibraries;
  }

  public void setNumberOfLibraries(Integer numberOfLibraries)
  {
    this.numberOfLibraries = numberOfLibraries;
  }

  public Integer getCountComments()
  {
    return countComments;
  }

  public void setCountComments(Integer countComments)
  {
    this.countComments = countComments;
  }

  public Double getRating()
  {
    return rating;
  }

  public void setRating(Double rating)
  {
    this.rating = rating;
  }

  public String getAuthor()
  {
    return author;
  }

  public void setAuthor(String author)
  {
    this.author = author;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }
}
