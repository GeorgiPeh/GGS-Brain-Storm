package com.example.digitallibrary.service;

import com.example.digitallibrary.model.Author;
import com.example.digitallibrary.model.Reader;
import com.example.digitallibrary.model.VerificationToken;
import com.example.digitallibrary.repository.AuthorRepository;
import com.example.digitallibrary.repository.ReadersRepository;
import com.example.digitallibrary.repository.VerificationTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class VerificationServiceImpl implements VerificationService
{
  private final VerificationTokenRepository verificationTokenRepository;
  private final ReadersRepository           readersRepository;
  private final AuthorRepository            authorRepository;

  public VerificationServiceImpl(VerificationTokenRepository verificationTokenRepository, ReadersRepository readersRepository, AuthorRepository authorRepository)
  {
    this.verificationTokenRepository = verificationTokenRepository;
    this.readersRepository = readersRepository;
    this.authorRepository = authorRepository;
  }

  @Override
  public boolean verify(String token)
  {
    Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
    try {
      if (verificationToken.isPresent() && verificationToken.get().getExpiryDate().isAfter(LocalDateTime.now())) {
        Optional<Reader> reader = readersRepository.findReaderByUsername(verificationToken.get().getUserName());
        if (reader.isPresent()) {
          reader.get().setEnabled(true);
          readersRepository.saveAndFlush(reader.get());
          return true;
        }
        Optional<Author> author = authorRepository.findAuthorByUsername(verificationToken.get().getUserName());
        if (author.isPresent()) {
          author.get().setEnabled(true);
          authorRepository.saveAndFlush(author.get());
          return true;
        }
      }
    }
    finally {
      if (verificationToken.isPresent()) {
        verificationTokenRepository.delete(verificationToken.get());
        verificationTokenRepository.flush();
      }
    }
    return false;
  }

  @Override
  public VerificationToken createToken(String username)
  {
    VerificationToken token = new VerificationToken();
    token.setUserName(username);
    token.setExpiryDate(LocalDateTime.now().plusDays(1));
    token.setToken(UUID.randomUUID().toString());
    verificationTokenRepository.saveAndFlush(token);
    return token;
  }
}
