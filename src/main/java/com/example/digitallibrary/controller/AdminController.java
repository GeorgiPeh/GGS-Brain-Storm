package com.example.digitallibrary.controller;

import com.example.digitallibrary.dto.ChangePasswordDTO;
import com.example.digitallibrary.dto.CreationDTO;
import com.example.digitallibrary.dto.UserDTO;
import com.example.digitallibrary.model.Admin;
import com.example.digitallibrary.service.AdminService;
import com.example.digitallibrary.service.AuthorsService;
import com.example.digitallibrary.service.EmailService;
import com.example.digitallibrary.service.ReadersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasAuthority('SUPERUSER') || hasAuthority('ADMIN')")
public class AdminController
{
  private final AdminService adminService;
  private final AuthorsService authorsService;
  private final ReadersService readersService;

  public AdminController(AdminService adminService, AuthorsService authorsService, ReadersService readersService)
  {
    this.adminService = adminService;
    this.authorsService = authorsService;
    this.readersService = readersService;
  }

  @GetMapping
  public List<UserDTO> getAdmins()
  {
    List<Admin> admins = adminService.getAdmins();
    List<UserDTO> UserDTOS = new ArrayList<>();
    for (Admin admin : admins) {
      UserDTO UserDTO = new UserDTO();
      UserDTO.setUsername(admin.getUsername());
      UserDTO.setFullName(admin.getFullName());
      UserDTO.setEmail(admin.getEmail());
      UserDTOS.add(UserDTO);
    }

    return UserDTOS;
  }

  @GetMapping(value = "/{admin}")
  public ResponseEntity<UserDTO> getAdmin(@PathVariable("admin") String username)
  {
    Optional<Admin> user = adminService.getAdmin(username);
    if (user.isPresent()) {
      UserDTO userDTO = new UserDTO();
      userDTO.setUsername(username);
      userDTO.setFullName(user.get().getFullName());
      userDTO.setEmail(user.get().getEmail());
      return ResponseEntity.ok(userDTO);
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping
  public ResponseEntity<?> createAdmin(@Valid @RequestBody() CreationDTO creationDTO) throws URISyntaxException
  {
    if (adminService.getAdmin(creationDTO.getUsername()).isPresent()
        ||authorsService.getAuthor(creationDTO.getUsername()).isPresent()
        ||readersService.getReader(creationDTO.getUsername()).isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    adminService.createAdmin(creationDTO.getEmail(),creationDTO.getUsername(), creationDTO.getFullName(), creationDTO.getPassword(), creationDTO.getRole());
    return ResponseEntity.created(new URI("/admins/" + creationDTO.getUsername())).build();
  }

  @DeleteMapping(value = "/{admin}")
  public ResponseEntity<?> disableAdmin(@PathVariable("admin") String userName)
  {
    if (adminService.disableAdmin(userName)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping(value = "/{admin}/password")
  public @ResponseBody
  ResponseEntity<?> changePassword(@PathVariable("admin") String userName,
                                   @Valid @RequestBody ChangePasswordDTO changePasswordDto,
                                   Principal principal)
  {
    if (!userName.equals(principal.getName()) || !adminService.changePassword(userName, changePasswordDto.oldPassword, changePasswordDto.newPassword)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong password or user name");
    }
    return ResponseEntity.ok().build();
  }

}
