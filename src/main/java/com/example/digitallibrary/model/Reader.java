package com.example.digitallibrary.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "readers")
public class Reader
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private Integer id;
  @NotNull
  @Size(min = 2, max = 40)
  @Pattern(regexp = "^[a-z0-8.\\-]+$")
  @Column(name = "username", unique = true)
  private String  username;

  @NotNull
  @Size(min = 5, max = 90)
  @Column(name = "fullName")
  private String  fullName;

  @NotNull
  @Size(min = 7)
  @Email
  private String email;

  @NotNull
  @Column(name = "role")
  private String role;

  @NotNull
  @Column(name = "password")
  private String  password;

  @Column(name = "is_enabled")
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
  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }
  public String getRole()
  {
    return role;
  }

  public void setRole(String role)
  {
    this.role = role;
  }

  public void setPassword(String password)
  {
    this.password = password;
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
