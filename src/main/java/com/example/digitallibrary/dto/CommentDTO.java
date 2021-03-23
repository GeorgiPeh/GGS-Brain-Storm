package com.example.digitallibrary.dto;

import com.sun.istack.NotNull;

import javax.validation.constraints.Min;

public class CommentDTO extends CreateCommentDTO
{
  @NotNull
  private Integer id;

  @NotNull
  @Min(2)
  private String userName;

  public Integer getId()
  {
    return id;
  }

  public CommentDTO setId(Integer id)
  {
    this.id = id;
    return this;
  }

  public String getUserName()
  {
    return userName;
  }

  public CommentDTO setUserName(String userName)
  {
    this.userName = userName;
    return this;
  }
}
