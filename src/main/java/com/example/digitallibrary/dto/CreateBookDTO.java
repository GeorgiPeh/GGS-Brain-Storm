package com.example.digitallibrary.dto;

import com.example.digitallibrary.model.Genre;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateBookDTO
{

  @NotNull(message = "Provide title!")
  @Size(min = 1, max = 60, message = "Title between 1 and 60 characters")
  private String  title;

  @NotNull(message = "Provide year!")
  private Integer year;

  private byte[] bookFile;

  public byte[] getBookFile()
  {
    return bookFile;
  }

  public void setBookFile(byte[] bookFile)
  {
    this.bookFile = bookFile;
  }

  public Genre getGenre()
  {
    return genre;
  }

  public void setGenre(Genre genre)
  {
    this.genre = genre;
  }

  private Genre genre;

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public Integer getYear()
  {
    return year;
  }

  public void setYear(Integer year)
  {
    this.year = year;
  }
}
