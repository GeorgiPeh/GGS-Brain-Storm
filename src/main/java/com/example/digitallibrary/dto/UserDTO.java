package com.example.digitallibrary.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO
{
  @NotNull
  @Size(min = 2, max = 40)
  @Pattern(regexp = "^[a-z0-8.\\-]+$")
  private String username;
  @NotNull
  @Size(min = 5, max = 90)
  private String fullName;
  @NotNull
  @Size(min = 7)
  @Email
  private String email;
  private String role;


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

}
