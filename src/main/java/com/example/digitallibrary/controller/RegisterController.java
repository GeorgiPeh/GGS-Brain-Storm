package com.example.digitallibrary.controller;

import com.example.digitallibrary.dto.CreationDTO;
import com.example.digitallibrary.model.Author;
import com.example.digitallibrary.model.Reader;
import com.example.digitallibrary.model.VerificationToken;
import com.example.digitallibrary.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RegisterController
{
  private final AuthorsService      authorsService;
  private final AdminService        adminService;
  private final ReadersService      readersService;
  private final EmailService        emailService;
  private final VerificationService verificationService;

  public RegisterController(AuthorsService authorsService, AdminService adminService, ReadersService readersService, EmailService emailService, VerificationService verificationService)
  {
    this.authorsService = authorsService;
    this.adminService = adminService;
    this.readersService = readersService;
    this.emailService = emailService;
    this.verificationService = verificationService;
  }

  @GetMapping("/registerAuth")
  private String registerAuth()
  {
    return "/registerAuth";
  }

  @PostMapping("/registerAuth")
  public String createAuthor(@Valid @ModelAttribute() CreationDTO creationDTO)
  {
    if (!creationDTO.getPassword().equals(creationDTO.getPasswordConfirmation())) {
      return "redirect:/error/registerPassNotMatch.html";
    }
    if (authorsService.getAuthor(creationDTO.getUsername()).isPresent()
        || adminService.getAdmin(creationDTO.getUsername()).isPresent()
        || readersService.getReader(creationDTO.getUsername()).isPresent()) {
      return "redirect:/error/registerUserIsPresent.html";
    }
    Author author = this.authorsService.createAuthor(creationDTO.getEmail(), creationDTO.getUsername(), creationDTO.getFullName(),
        creationDTO.getPassword(), creationDTO.getRole());
    VerificationToken token = verificationService.createToken(author.getUsername());
    emailService.sendMail(author.getEmail(), "Registration Verification", "http://localhost:8080/verifyRegistration/" + token.getToken());
    return "redirect:/login";
  }

  @GetMapping("/registerReader")
  private String registerReader()
  {
    return "/registerReader";
  }

  @PostMapping("/registerReader")
  public String createReader(@Valid @ModelAttribute() CreationDTO creationDTO)
  {
    if (!creationDTO.getPassword().equals(creationDTO.getPasswordConfirmation())) {
      return "redirect:/error/registerPassNotMatch.html";
    }
    if (authorsService.getAuthor(creationDTO.getUsername()).isPresent()
        || adminService.getAdmin(creationDTO.getUsername()).isPresent()
        || readersService.getReader(creationDTO.getUsername()).isPresent()) {
      return "redirect:/error/registerUserIsPresent.html";
    }
    Reader reader = this.readersService.createReader(creationDTO.getEmail(), creationDTO.getUsername(), creationDTO.getFullName(),
        creationDTO.getPassword(), creationDTO.getRole());
    VerificationToken token = verificationService.createToken(reader.getUsername());
    emailService.sendMail(reader.getEmail(), "Registration Verification", "http://localhost:8080/verifyRegistration/" + token.getToken());
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String getLoginPage()
  {
    return "/login";
  }

  @GetMapping("/verifyRegistration/{token}")
  public String verify(@PathVariable String token)
  {
    if (verificationService.verify(token)) {
      return "redirect:/login";
    }
    return "redirect:/error/verifyTokenNotMatch.html";
  }
}