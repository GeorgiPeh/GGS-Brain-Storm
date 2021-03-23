package com.example.digitallibrary.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "authors")
public class Author
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;

  @Column(name = "username", unique = true)
  @NotNull
  @Size(min = 2, max = 40)
  @Pattern(regexp = "^[a-z0-8.\\-]+$")
  private String    username;

  @NotNull
  @Size(min = 7)
  @Email
  @Column(name = "email")
  private String    email;

  @NotNull
  @Column(name = "role")
  private String role;

  @NotNull
  @Size(min = 5, max = 90)
  @Column(name = "fullName")
  private String  fullName;

  @NotNull
  @Column(name = "password", nullable = false)
  private String  password;

  @Column(name = "rating")
  private Double rating;

  @Column(name = "book_count")
  private Integer numberOfBooks;

  @Column(name = "is_enabled", nullable = false)
  private boolean isEnabled;

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getUsername()
  {
    return username;
  }
  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getFullName()
  {
    return fullName;
  }

  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getRole()
  {
    return role;
  }

  public void setRole(String role)
  {
    this.role = role;
  }
  public Double getRating()
  {
    return rating;
  }

  public void setRating(Double rating)
  {
    this.rating = rating;
  }

  public Integer getNumberOfBooks()
  {
    return numberOfBooks;
  }

  public void setNumberOfBooks(Integer numberOfBooks)
  {
    this.numberOfBooks = numberOfBooks;
  }

  public boolean isEnabled()
  {
    return isEnabled;
  }

  public void setEnabled(boolean enabled)
  {
    isEnabled = enabled;
  }
}
