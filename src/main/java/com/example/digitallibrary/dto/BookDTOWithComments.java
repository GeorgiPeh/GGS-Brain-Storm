package com.example.digitallibrary.dto;

import java.util.ArrayList;
import java.util.List;

public class BookDTOWithComments extends BookDTO
{

  private List<CreateCommentDTO> commentList = new ArrayList<>();

  public BookDTOWithComments()
  {
  }

  public List<CreateCommentDTO> getCommentList()
  {

    return
        commentList;
  }

  public BookDTOWithComments setCommentList(List<CreateCommentDTO> commentList)
  {
    this.commentList = commentList;
    return this;
  }
}
