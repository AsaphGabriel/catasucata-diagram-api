package com.example.catasucata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.catasucata.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Aqui poderemos adicionar métodos específicos no futuro, como buscar por email
    // Ex: Optional<Cliente> findByEmail(String email);
}