package com.example.digitallibrary.service;

public interface EmailService
{
  void sendMail(String to, String subject, String msg);
}
