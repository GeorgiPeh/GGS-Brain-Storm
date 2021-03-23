package com.example.digitallibrary.service;

import com.example.digitallibrary.model.Admin;


import java.util.List;
import java.util.Optional;

public interface AdminService
{
  List<Admin> getAdmins();

  Optional<Admin> getAdmin(String username);

  Admin createAdmin(String email, String username, String fullName, String password, String role);

  boolean disableAdmin(String userName);

  boolean changePassword(String userName, String oldPassword, String newPassword);
}
