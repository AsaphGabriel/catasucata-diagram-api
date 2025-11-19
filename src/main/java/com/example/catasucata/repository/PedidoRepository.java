package com.example.catasucata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.catasucata.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}