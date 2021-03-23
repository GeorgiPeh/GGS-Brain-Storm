package com.example.digitallibrary.dto;

import java.time.LocalDateTime;

public class BookLibraryDTO extends BookDTO
{
  private LocalDateTime lastRead;

  public LocalDateTime getLastRead()
  {
    return lastRead;
  }

  public void setLastRead(LocalDateTime lastRead)
  {
    this.lastRead = lastRead;
  }
}
