package com.example.digitallibrary.service;

import com.example.digitallibrary.model.*;
import com.example.digitallibrary.repository.BookRepository;
import com.example.digitallibrary.repository.LibraryRepository;
import com.example.digitallibrary.repository.ReadersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class ReadersServiceImpl implements ReadersService
{
  private final ReadersRepository readersRepository;
  private final PasswordEncoder   passwordEncoder;
  private final LibraryRepository libraryRepository;
  private final BookRepository    bookRepository;

  @Autowired
  public ReadersServiceImpl(ReadersRepository readersRepository, PasswordEncoder passwordEncoder, LibraryRepository libraryRepository, BookRepository bookRepository)
  {
    this.readersRepository = readersRepository;
    this.passwordEncoder = passwordEncoder;
    this.libraryRepository = libraryRepository;
    this.bookRepository = bookRepository;
  }

  @Override
  public List<Reader> getReaders()
  {
    return readersRepository.findAll();
  }

  @Override
  public Optional<Reader> getReader(String username)
  {
    return readersRepository.findReaderByUsername(username);
  }

  @Override
  public Reader createReader(String email, String username, String fullName, String password, String role)
  {
    Reader reader = new Reader();
    reader.setEmail(email);
    reader.setUsername(username);
    reader.setFullName(fullName);
    reader.setPassword(passwordEncoder.encode(password));
    reader.setEnabled(false);
    reader.setRole("READER");
    readersRepository.saveAndFlush(reader);
    return reader;
  }

  @Override
  public boolean disableReader(String userName)
  {
    Optional<Reader> reader = getReader(userName);
    if (reader.isPresent()) {
      reader.get().setEnabled(false);
      readersRepository.saveAndFlush(reader.get());
      return true;
    }
    return false;
  }

  @Override
  public boolean changePassword(String userName, String oldPassword, String newPassword)
  {
    Optional<Reader> reader = getReader(userName);
    if (reader.isPresent() && reader.get().isEnabled()) {
      if (new BCryptPasswordEncoder().matches(oldPassword, reader.get().getPassword())) {
        reader.get().setPassword(passwordEncoder.encode(newPassword));
        readersRepository.saveAndFlush(reader.get());
        return true;
      }
    }
    return false;
  }

  @Override
  public List<Library> getLibrary(Reader reader)
  {
    return libraryRepository.findByReaderOrderByLastReadDesc(reader);
  }

  @Override
  public Optional<Library> getLibrary(Reader reader, Book book)
  {
    List<Library> libraries = libraryRepository.findByReaderAndBook(reader, book);
    if (libraries.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(libraries.get(0));
  }

  @Override
  public void updateLibrary(Library library)
  {
    library.setLastRead(LocalDateTime.now());
    libraryRepository.saveAndFlush(library);
  }

  @Override
  public void addToLibrary(Reader reader, Book book)
  {
    Library library = new Library();
    library.setReader(reader);
    library.setBook(book);
    library.setLastRead(LocalDateTime.now());
    libraryRepository.saveAndFlush(library);
    book.setNumOfLibraries(book.getNumOfLibraries() + 1);
    bookRepository.saveAndFlush(book);
  }

  @Override
  public void removeFromLibrary(Library library)
  {
    libraryRepository.delete(library);
    libraryRepository.flush();
    Book book = library.getBook();
    book.setNumOfLibraries(book.getNumOfLibraries() - 1);
    bookRepository.saveAndFlush(book);
  }
}
