package com.example.digitallibrary.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VerificationToken
{
  private static final int EXPIRATION = 60 * 24;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @Column(name = "token", unique = true, nullable = false)
  private String token;

  @Column(name = "userName", unique = true, nullable = false)
  private String userName;

  @Column(name = "expiryDate", nullable = false)
  private LocalDateTime expiryDate;

  public LocalDateTime getExpiryDate()
  {
    return expiryDate;
  }

  public void setExpiryDate(LocalDateTime expiryDate)
  {
    this.expiryDate = expiryDate;
  }

  public String getToken()
  {
    return token;
  }

  public void setToken(String token)
  {
    this.token = token;
  }

  public String getUserName()
  {
    return userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }
}
