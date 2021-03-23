package com.example.digitallibrary.repository;

import com.example.digitallibrary.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource(collectionResourceRel = "admins", path = "admins")
public interface AdminRepository extends JpaRepository<Admin, Integer>
{
  Optional<Admin> findAdminByUsername(String userName);
}
