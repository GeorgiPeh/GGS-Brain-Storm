package com.example.digitallibrary.repository;

import com.example.digitallibrary.model.Reader;
import com.example.digitallibrary.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "tokens", path = "tokens")
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer>
{
  Optional<VerificationToken> findByToken(String token);
}
