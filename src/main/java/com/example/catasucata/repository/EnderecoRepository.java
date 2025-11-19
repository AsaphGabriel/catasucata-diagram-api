package com.example.catasucata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.catasucata.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}