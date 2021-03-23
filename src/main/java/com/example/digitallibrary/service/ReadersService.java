package com.example.digitallibrary.service;

import com.example.digitallibrary.model.Admin;
import com.example.digitallibrary.model.Book;
import com.example.digitallibrary.model.Library;
import com.example.digitallibrary.model.Reader;

import java.util.List;
import java.util.Optional;

public interface ReadersService
{
  List<Reader> getReaders();

  Optional<Reader> getReader(String username);

  Reader createReader(String email, String username, String fullName, String password, String role);

  boolean disableReader(String userName);

  boolean changePassword(String userName, String oldPassword, String newPassword);

  List<Library> getLibrary(Reader reader);

  Optional<Library> getLibrary(Reader reader, Book book);

  void updateLibrary(Library library);

  void addToLibrary(Reader reader, Book book);

  void removeFromLibrary(Library library);
}
