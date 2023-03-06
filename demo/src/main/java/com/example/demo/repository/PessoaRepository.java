package com.example.demo.repository;

import com.example.demo.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
    //Optional<Pessoa> findAllById(long id);
}
