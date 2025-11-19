package com.example.catasucata.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType; // NOVO IMPORT!
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    private LocalDateTime dataPedido;

    private String status; 

    private Double valorTotal;

    // Relacionamento: Pedido pertence a um Cliente
    @JsonIgnore // <--- QUEBRA O LOOP!
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Relacionamento: Um Pedido tem Muitos ItensPedido
    // 'mappedBy' aponta para o nome do atributo na classe ItemPedido
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    // Construtor Padrão (necessário para JPA)
    public Pedido() {
        this.dataPedido = LocalDateTime.now(); 
        this.status = "PENDENTE"; 
        this.valorTotal = 0.0;
    }
    
    // Construtor com Cliente (para facilitar a criação no Service)
    public Pedido(Cliente cliente) {
        this(); // Chama o construtor padrão para iniciar data e status
        this.cliente = cliente;
    }

    // Getters e Setters
    public Long getIdPedido() { return idPedido; }
    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }

    public LocalDateTime getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDateTime dataPedido) { this.dataPedido = dataPedido; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<ItemPedido> getItens() { return itens; }
    public void setItens(List<ItemPedido> itens) { this.itens = itens; }
}