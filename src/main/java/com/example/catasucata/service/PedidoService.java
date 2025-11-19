package com.example.catasucata.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.catasucata.model.Cliente;
import com.example.catasucata.model.Item;
import com.example.catasucata.model.ItemPedido;
import com.example.catasucata.model.Pedido;
import com.example.catasucata.repository.ClienteRepository;
import com.example.catasucata.repository.ItemRepository;
import com.example.catasucata.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ItemRepository itemRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository,
                         ItemRepository itemRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Pedido criarPedido(Long clienteId, List<ItemPedido> itensRecebidos) {
        // 1. Validar Cliente
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + clienteId));

        // 2. Validar itens
        if (itensRecebidos == null || itensRecebidos.isEmpty()) {
            throw new RuntimeException("A lista de itens do pedido não pode ser vazia.");
        }

        // 3. Criar o novo Pedido associado ao cliente
        Pedido novoPedido = new Pedido(cliente);
        Double valorTotal = 0.0;

        for (ItemPedido itemRecebido : itensRecebidos) {
            if (itemRecebido == null || itemRecebido.getItem() == null || itemRecebido.getItem().getId() == null) {
                throw new RuntimeException("Item inválido: o ID do produto não pode ser nulo.");
            }

            Long itemId = itemRecebido.getItem().getId();
            Item produto = itemRepository.findById(itemId)
                    .orElseThrow(() -> new RuntimeException("Item não encontrado com ID: " + itemId));

            Integer quantidadeComprada = itemRecebido.getQuantidade();
            if (quantidadeComprada == null || quantidadeComprada <= 0) {
                throw new RuntimeException("Quantidade inválida para o item de ID: " + itemId);
            }

            // Verificar estoque
            if (produto.getEstoque() == null || produto.getEstoque() < quantidadeComprada) {
                throw new RuntimeException("Estoque insuficiente para o item: " + produto.getNome());
            }

            // Atualizar estoque
            produto.setEstoque(produto.getEstoque() - quantidadeComprada);
            itemRepository.save(produto);

            // Preencher os dados de ItemPedido
            itemRecebido.setPedido(novoPedido);
            itemRecebido.setItem(produto);
            itemRecebido.setPrecoUnitario(produto.getPreco());

            // Somar ao valor total
            valorTotal += produto.getPreco() * quantidadeComprada;

            // Adicionar ao pedido
            novoPedido.getItens().add(itemRecebido);
        }

        // Definir valor total e salvar
        novoPedido.setValorTotal(valorTotal);
        return pedidoRepository.save(novoPedido);
    }
}
