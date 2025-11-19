package com.example.catasucata.model;

import java.io.Serializable;
import java.util.Objects;

public class ItemPedidoId implements Serializable {
    
    // Nomes devem ser IGUAIS aos atributos da classe ItemPedido (sem a tipagem Long)
    private Long pedido; 
    private Long item;   

    // Construtor Padrão (necessário)
    public ItemPedidoId() {}

    // Construtor com Chaves (útil)
    public ItemPedidoId(Long pedido, Long item) {
        this.pedido = pedido;
        this.item = item;
    }

    // --- Métodos Essenciais (Gerados) ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoId that = (ItemPedidoId) o;
        return Objects.equals(pedido, that.pedido) && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, item);
    }
    
    // Getters e Setters (Opcionais, mas boas práticas)
    public Long getPedido() { return pedido; }
    public void setPedido(Long pedido) { this.pedido = pedido; }
    public Long getItem() { return item; }
    public void setItem(Long item) { this.item = item; }
}