package com.example.digitallibrary.service;

import com.example.digitallibrary.model.VerificationToken;

public interface VerificationService
{
  boolean verify(String token);

  VerificationToken createToken(String username);
}
