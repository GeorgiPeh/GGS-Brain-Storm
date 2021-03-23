package com.example.digitallibrary.config;

import com.example.digitallibrary.model.Admin;
import com.example.digitallibrary.model.Author;
import com.example.digitallibrary.model.Reader;
import com.example.digitallibrary.service.AdminService;
import com.example.digitallibrary.service.AuthorsService;
import com.example.digitallibrary.service.ReadersService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
  private AuthorsService authorsService;
  private ReadersService readersService;
  private AdminService   adminService;
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  public UserDetailsServiceImpl(AuthorsService authorsService, ReadersService readersService, AdminService adminService, BCryptPasswordEncoder bCryptPasswordEncoder)
  {
    this.authorsService = authorsService;
    this.readersService = readersService;
    this.adminService = adminService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {
    if (authorsService.getAuthors()== null && readersService.getReaders() == null && adminService.getAdmins()==null){
      this.adminService.createAdmin("g.pehlivanov07@gmail.com", "admin", "Admin", bCryptPasswordEncoder.encode("Admin123!"), "SUPERUSER");
    }
    Optional<Author> optionalAuthor = authorsService.getAuthor(username);
    if (optionalAuthor.isPresent()) {
      Author author = optionalAuthor.get();
      if (author.isEnabled()) {
        return org.springframework.security.core.userdetails.User.withUsername(username)
            .password(author.getPassword())
            .roles("AUTHOR")
            .build();
      }
    }
    else {
      Optional<Reader> optionalReader = readersService.getReader(username);
      if (optionalReader.isPresent()) {
        Reader reader = optionalReader.get();
        if (reader.isEnabled()) {
          return org.springframework.security.core.userdetails.User.withUsername(username)
              .password(reader.getPassword())
              .roles("READER")
              .build();
        }
      }
      else {
        Optional<Admin> optionalAdmin = adminService.getAdmin(username);
        if (optionalAdmin.isPresent()) {
          Admin admin = optionalAdmin.get();
          if (admin.isEnabled()) {
            return org.springframework.security.core.userdetails.User.withUsername(username)
                .password(admin.getPassword())
                .roles("ADMIN")
                .build();
          }
        }
      }
    }
    throw new UsernameNotFoundException("User not found.");
  }
}
