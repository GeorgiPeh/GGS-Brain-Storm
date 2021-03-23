package com.example.digitallibrary.service;

import com.example.digitallibrary.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorsService
{
  List<Author> getAuthors();

  Optional<Author> getAuthor(String username);

  Author createAuthor(String email, String username, String fullName, String password, String role);

  boolean disableAuthor(String userName);

  boolean changePassword(String userName, String oldPassword, String newPassword);
}
