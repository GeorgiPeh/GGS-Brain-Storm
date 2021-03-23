package com.example.digitallibrary.service;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService
{

  private MailSender mailSender;

  public EmailServiceImpl(MailSender mailSender)
  {
    this.mailSender = mailSender;
  }

  @Override
  public void sendMail(String to, String subject, String msg) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("ggsbrains@gmail.com");
    message.setTo(to);
    message.setSubject(subject);

    message.setText(msg);
    mailSender.send(message);
  }
}
