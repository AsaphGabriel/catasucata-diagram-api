package com.example.catasucata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.catasucata.model.ItemPedido;
import com.example.catasucata.model.ItemPedidoId;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoId> {
}