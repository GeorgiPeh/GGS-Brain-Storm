package com.example.digitallibrary.repository;

import com.example.digitallibrary.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "readers", path = "readers")
public interface ReadersRepository extends JpaRepository<Reader, Integer>
{
  Optional<Reader> findReaderByUsername(String userName);
}
