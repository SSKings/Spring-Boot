package com.sskings.shopping_delivery.services;

import com.sskings.shopping_delivery.exceptions.EstoqueInsuficienteException;
import com.sskings.shopping_delivery.exceptions.ItemNaoEncontradoException;
import com.sskings.shopping_delivery.exceptions.ItemPedidoNaoEncontradoException;
import com.sskings.shopping_delivery.exceptions.PedidoNaoEncontradoException;
import com.sskings.shopping_delivery.models.ItemModel;
import com.sskings.shopping_delivery.models.ItemPedidoModel;
import com.sskings.shopping_delivery.models.PedidoModel;
import com.sskings.shopping_delivery.repositories.ItemPedidoRepository;
import com.sskings.shopping_delivery.repositories.ItemRepository;
import com.sskings.shopping_delivery.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemPedidoService {

    private final ItemRepository itemRepository;
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoService pedidoService;

    @Transactional
    public ItemPedidoModel salvar(ItemPedidoModel itemPedidoModel) {
        ItemModel item = itemRepository.findById(itemPedidoModel.getItem().getId())
                .orElseThrow(() -> new ItemNaoEncontradoException("Item não encontrado."));
        
        PedidoModel pedido = pedidoRepository.findById(itemPedidoModel.getPedido().getId())
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado."));
        
        // Validar estoque
        long quantidadeSolicitada = itemPedidoModel.getQuantidade();
        if (item.getEstoque() < quantidadeSolicitada) {
            throw new EstoqueInsuficienteException(
                String.format("Estoque insuficiente. Disponível: %d, Solicitado: %d", 
                    item.getEstoque(), quantidadeSolicitada));
        }
        
        // Definir preço unitário do item se não estiver definido
        if (itemPedidoModel.getPrecoUnitario() == null) {
            itemPedidoModel.setPrecoUnitario(item.getPreco());
        }
        
        // Atualizar estoque
        int novoEstoque = item.getEstoque() - (int) quantidadeSolicitada;
        item.setEstoque(novoEstoque);
        itemRepository.save(item);
        
        // Salvar item do pedido
        itemPedidoModel.setItem(item);
        itemPedidoModel.setPedido(pedido);
        ItemPedidoModel itemPedidoSalvo = itemPedidoRepository.save(itemPedidoModel);
        
        // Recalcular total do pedido
        pedidoService.calcularTotalPedido(pedido.getId());
        
        return itemPedidoSalvo;
    }

    public List<ItemPedidoModel> listar() {
        List<ItemPedidoModel> itemsPedidos = itemPedidoRepository.findAll();
        if (itemsPedidos.isEmpty()){
            return new ArrayList<>();
        }
        return itemsPedidos;
    }

    public ItemPedidoModel buscarItemPedidoPorId(long id) {
        return itemPedidoRepository.findById(id)
                .orElseThrow(() -> new ItemPedidoNaoEncontradoException("Item do pedido não encontrado."));
    }

    @Transactional
    public void removerPorId(long id) {
        ItemPedidoModel itemPedido = buscarItemPedidoPorId(id);
        
        // Restaurar estoque
        ItemModel item = itemPedido.getItem();
        item.setEstoque(item.getEstoque() + itemPedido.getQuantidade().intValue());
        itemRepository.save(item);
        
        // Remover item do pedido
        Long pedidoId = itemPedido.getPedido().getId();
        itemPedidoRepository.deleteById(id);
        
        // Recalcular total do pedido
        pedidoService.calcularTotalPedido(pedidoId);
    }
}


