package com.example.catasucata.model;

import com.fasterxml.jackson.annotation.JsonIgnore; // NOVO IMPORT

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@IdClass(ItemPedidoId.class) 
@Table(name = "item_pedido")
public class ItemPedido {

    // --- Chaves Compostas ---
    
    @JsonIgnore // <--- QUEBRA O CICLO: Não precisa serializar o Pedido inteiro aqui.
    @Id
    @ManyToOne
    @JoinColumn(name = "pedido_id") 
    private Pedido pedido;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id") 
    private Item item;

    // --- Atributos da Relação ---
    private Integer quantidade;
    
    @Column(name = "preco_unitario")
    private Double precoUnitario; 

    // Construtor Padrão (necessário para JPA)
    public ItemPedido() {}

    // Construtor Completo
    public ItemPedido(Pedido pedido, Item item, Integer quantidade, Double precoUnitario) {
        this.pedido = pedido;
        this.item = item;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    // Getters e Setters
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(Double precoUnitario) { this.precoUnitario = precoUnitario; }
}