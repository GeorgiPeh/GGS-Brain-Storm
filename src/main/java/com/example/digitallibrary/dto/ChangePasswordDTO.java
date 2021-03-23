package com.example.digitallibrary.dto;

import com.example.digitallibrary.validation.ValidPassword;

public class ChangePasswordDTO
{
  @ValidPassword
  public String oldPassword;
  @ValidPassword
  public String newPassword;
  @ValidPassword
  public String newPasswordConfirm;



  public String getOldPassword()
  {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword)
  {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword()
  {
    return newPassword;
  }

  public void setNewPassword(String newPassword)
  {
    this.newPassword = newPassword;
  }
  public String getNewPasswordConfirm()
  {
    return newPasswordConfirm;
  }

  public void setNewPasswordConfirm(String newPasswordConfirm)
  {
    this.newPasswordConfirm = newPasswordConfirm;
  }
}
