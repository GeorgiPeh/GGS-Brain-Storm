package com.example.digitallibrary.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Range;

public class CreateCommentDTO
{
  @Size(min = 2, max = 255, message = "Between 2 and 255 characters!")
  @NotNull
  private String  content;
  @Range(min = 1, max = 10)
  private Integer rating;

  public Integer getRating()
  {
    return rating;
  }

  public void setRating(Integer rating)
  {
    this.rating = rating;
  }

  private LocalDateTime time;

  public CreateCommentDTO()
  {
  }

  public String getContent()
  {
    return content;
  }

  public CreateCommentDTO setContent(String content)
  {
    this.content = content;
    return this;
  }

  public LocalDateTime getTime()
  {
    return time;
  }

  public CreateCommentDTO setTime(LocalDateTime time)
  {
    this.time = time;
    return this;
  }
}
