package com.example.catasucata.model;

import com.fasterxml.jackson.annotation.JsonBackReference; 

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;

    private String rua; 
    private String cidade;
    private String cep;

    // Relacionamento: Um endereço pertence a um cliente (Lado Referência Reversa / Filho)
    @JsonBackReference 
    @OneToOne(fetch = FetchType.LAZY) // <-- CORRETO (JÁ ESTAVA LAZY)
    @JoinColumn(name = "cliente_id") 
    private Cliente cliente;

    // Construtor Padrão
    public Endereco() {
    }

    // Getters e Setters
    public Long getIdEndereco() { return idEndereco; }
    public void setIdEndereco(Long idEndereco) { this.idEndereco = idEndereco; }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}