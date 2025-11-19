package com.example.catasucata.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.catasucata.model.Cliente;
import com.example.catasucata.model.Endereco;
import com.example.catasucata.repository.ClienteRepository;
import com.example.catasucata.repository.EnderecoRepository;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;

    public EnderecoController(EnderecoRepository enderecoRepository,
                              ClienteRepository clienteRepository) {
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
    }

    // POST - Cadastrar endereço para um cliente
    @PostMapping("/{clienteId}")
    public ResponseEntity<Endereco> cadastrar(@PathVariable Long clienteId,
                                              @RequestBody Endereco endereco) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + clienteId));

        endereco.setCliente(cliente);
        Endereco salvo = enderecoRepository.save(endereco);
        return ResponseEntity.status(201).body(salvo);
    }

    // GET - Listar todos os endereços
    @GetMapping
    public List<Endereco> listar() {
        return enderecoRepository.findAll();
    }

    // GET - Buscar endereço por ID
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
        return enderecoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
