package com.example.digitallibrary.service;

import com.example.digitallibrary.model.Admin;
import com.example.digitallibrary.repository.AdminRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService
{
  private final AdminRepository adminRepository;
  private final PasswordEncoder passwordEncoder;

  public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder)
  {
    this.adminRepository = adminRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public List<Admin> getAdmins()
  {
    return adminRepository.findAll();
  }

  @Override
  public Optional<Admin> getAdmin(String username)
  {
    return adminRepository.findAdminByUsername(username);
  }

  @Override
  public Admin createAdmin(String email, String username, String fullName, String password, String role)
  {
    Admin admin = new Admin();
    admin.setEmail(email);
    admin.setUsername(username);
    admin.setFullName(fullName);
    admin.setPassword(passwordEncoder.encode(password));
    admin.setRole("ADMIN");
    adminRepository.saveAndFlush(admin);
    return admin;
  }

  @Override
  public boolean disableAdmin(String userName)
  {
    Optional<Admin> admin = getAdmin(userName);
    if (admin.isPresent()) {
      admin.get().setEnabled(false);
      adminRepository.saveAndFlush(admin.get());
      return true;
    }
    return false;
  }

  @Override
  public boolean changePassword(String userName, String oldPassword, String newPassword)
  {
    Optional<Admin> admin = getAdmin(userName);
    if (admin.isPresent() && admin.get().isEnabled()) {
      if (new BCryptPasswordEncoder().matches(oldPassword, admin.get().getPassword())) {
        admin.get().setPassword(passwordEncoder.encode(newPassword));
        adminRepository.saveAndFlush(admin.get());
        return true;
      }
    }
    return false;
  }

}
