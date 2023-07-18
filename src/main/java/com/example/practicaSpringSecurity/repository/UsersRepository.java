package com.example.practicaSpringSecurity.repository;

import com.example.practicaSpringSecurity.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Usuario, Integer> {
}
