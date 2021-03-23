package com.example.digitallibrary.service;

import com.example.digitallibrary.model.Admin;
import com.example.digitallibrary.model.Author;
import com.example.digitallibrary.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class AuthorsServiceImpl implements AuthorsService
{
  private final AuthorRepository authorRepository;
  private final PasswordEncoder  passwordEncoder;

  @Autowired
  public AuthorsServiceImpl(AuthorRepository authorRepository, PasswordEncoder passwordEncoder)
  {
    this.authorRepository = authorRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public List<Author> getAuthors()
  {
    return authorRepository.findAll();
  }

  @Override
  public Optional<Author> getAuthor(String username)
  {
    return authorRepository.findAuthorByUsername(username);
  }

  @Override
  public Author createAuthor( String email, String username, String fullName, String password, String role)
  {
    Author author = new Author();
    author.setEmail(email);
    author.setUsername(username);
    author.setFullName(fullName);
    author.setPassword(passwordEncoder.encode(password));
    author.setEnabled(false);
    author.setRole("AUTHOR");
    authorRepository.saveAndFlush(author);
    return author;
  }

  @Override
  public boolean disableAuthor(String userName)
  {
    Optional<Author> author = getAuthor(userName);
    if (author.isPresent()) {
      author.get().setEnabled(false);
      authorRepository.saveAndFlush(author.get());
      return true;
    }
    return false;
  }

  @Override
  public boolean changePassword(String userName, String oldPassword, String newPassword)
  {
    Optional<Author> author = getAuthor(userName);
    if (author.isPresent() && author.get().isEnabled()) {
      if (new BCryptPasswordEncoder().matches(oldPassword, author.get().getPassword())) {
        author.get().setPassword(passwordEncoder.encode(newPassword));
        authorRepository.saveAndFlush(author.get());
        return true;
      }
    }
    return false;
  }
}
