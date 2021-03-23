package com.example.digitallibrary.dto;

public class CreationDTO extends UserDTO
{

  private String password;

  private String passwordConfirmation;

  public CreationDTO(String passwordConfirmation)
  {
    this.passwordConfirmation = passwordConfirmation;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getPasswordConfirmation()
  {
    return passwordConfirmation;
  }

}
