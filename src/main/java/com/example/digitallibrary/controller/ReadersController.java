package com.example.digitallibrary.controller;

import com.example.digitallibrary.dto.*;
import com.example.digitallibrary.model.Book;
import com.example.digitallibrary.model.Library;
import com.example.digitallibrary.model.Reader;
import com.example.digitallibrary.service.AdminService;
import com.example.digitallibrary.service.AuthorsService;
import com.example.digitallibrary.service.BookService;
import com.example.digitallibrary.service.ReadersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reader")
public class ReadersController
{
  private final ReadersService readersService;
  private final BookService    bookService;

  public ReadersController(ReadersService readersService, AdminService adminService, AuthorsService authorsService, BookService bookService)
  {
    this.readersService = readersService;
    this.bookService = bookService;
  }

  @GetMapping
  public List<UserDTO> getReaders()
  {
    List<Reader> readers = readersService.getReaders();
    List<UserDTO> userDTOS;
    userDTOS = new ArrayList<>();
    for (Reader reader : readers) {
      UserDTO userDTO = new UserDTO();
      userDTO.setUsername(reader.getUsername());
      userDTO.setFullName(reader.getFullName());
      userDTO.setEmail(reader.getEmail());
      userDTOS.add(userDTO);
    }

    return userDTOS;
  }

  @GetMapping(value = "/{reader}")
  public ResponseEntity<UserDTO> getReader(@PathVariable("reader") String username)
  {
    Optional<Reader> reader = readersService.getReader(username);
    if (reader.isPresent()) {
      UserDTO userDTO = new UserDTO();
      userDTO.setUsername(username);
      userDTO.setFullName(reader.get().getFullName());
      userDTO.setEmail(reader.get().getEmail());
      return ResponseEntity.ok(userDTO);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping(value = "/{reader}")
  public ResponseEntity<?> disableReader(@PathVariable("reader") String userName)
  {
    if (readersService.disableReader(userName)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping(value = "/{reader}/password")
  public @ResponseBody
  ResponseEntity<?> changePassword(@PathVariable("reader") String userName,
                                   @Valid @RequestBody ChangePasswordDTO changePasswordDto,
                                   Principal principal)
  {
    if (!userName.equals(principal.getName()) || !readersService.changePassword(userName, changePasswordDto.oldPassword, changePasswordDto.newPassword)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong password or user name");
    }
    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/{reader}/library")
  public ResponseEntity<List<BookLibraryDTO>> getLibrary(@PathVariable("reader") String userName, Principal principal)
  {
    Optional<Reader> reader = readersService.getReader(userName);
    if (reader.isPresent() && reader.get().getUsername().equals(principal.getName())) {
      List<Library> libraryElements = readersService.getLibrary(reader.get());
      List<BookLibraryDTO> library = new ArrayList<>();
      for (Library libraryElement : libraryElements) {
        BookLibraryDTO bookLibraryDTO = new BookLibraryDTO();
        bookLibraryDTO.setAuthor(libraryElement.getBook().getAuthor().getFullName());
        bookLibraryDTO.setLastRead(libraryElement.getLastRead());
        bookLibraryDTO.setCountComments(libraryElement.getBook().getNumOfRatings());
        bookLibraryDTO.setGenre(libraryElement.getBook().getGenre());
        bookLibraryDTO.setNumberOfLibraries(libraryElement.getBook().getNumOfLibraries());
        bookLibraryDTO.setRating(libraryElement.getBook().getRating());
        bookLibraryDTO.setYear(libraryElement.getBook().getYear());
        bookLibraryDTO.setTitle(libraryElement.getBook().getTitle());
        library.add(bookLibraryDTO);
      }
      return ResponseEntity.ok(library);
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping(value = "/{reader}/library/{bookId}")
  public ResponseEntity<byte[]> read(@PathVariable("reader") String userName, @PathVariable("reader") Integer bookId, Principal principal)
  {
    Optional<Reader> reader = readersService.getReader(userName);
    if (reader.isPresent() && reader.get().getUsername().equals(principal.getName())) {
      Optional<Book> book = bookService.getBook(bookId);
      if (book.isPresent()) {
        Optional<Library> library = readersService.getLibrary(reader.get(), book.get());
        if (library.isPresent()) {
          readersService.updateLibrary(library.get());
          return ResponseEntity.ok(book.get().getBookFile());
        }
      }
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping(value = "/{reader}/library/{bookId}")
  public ResponseEntity<?> addToLibrary(@PathVariable("reader") String userName, @PathVariable("bookId") Integer bookId, Principal principal)
  {
    Optional<Reader> reader = readersService.getReader(userName);
    if (reader.isPresent() && reader.get().getUsername().equals(principal.getName())) {
      Optional<Book> book = bookService.getBook(bookId);
      if (book.isPresent()) {
        Optional<Library> library = readersService.getLibrary(reader.get(), book.get());
        if (library.isPresent()){
          return ResponseEntity.ok().build();
        }
        readersService.addToLibrary(reader.get(), book.get());
        return ResponseEntity.status(HttpStatus.CREATED).build();
      }
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping(value = "/{reader}/library/{bookId}")
  public ResponseEntity<?> removeFromLibrary(@PathVariable("reader") String userName, @PathVariable("bookId") Integer bookId, Principal principal)
  {
    Optional<Reader> reader = readersService.getReader(userName);
    if (reader.isPresent() && reader.get().getUsername().equals(principal.getName())) {
      Optional<Book> book = bookService.getBook(bookId);
      if (book.isPresent()) {
        Optional<Library> library = readersService.getLibrary(reader.get(), book.get());
        if (library.isPresent()) {
          readersService.removeFromLibrary(library.get());
          return ResponseEntity.ok().build();
        }
      }
    }
    return ResponseEntity.notFound().build();
  }
}
