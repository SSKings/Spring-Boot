package com.sskings.shopping_delivery.controllers;

import com.sskings.shopping_delivery.models.ItemPedidoModel;
import com.sskings.shopping_delivery.services.ItemPedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-pedido")
@RequiredArgsConstructor
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    @PostMapping
    public ResponseEntity<ItemPedidoModel> salvarItemPedido(@Valid @RequestBody ItemPedidoModel itemPedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemPedidoService.salvar(itemPedido));
    }

    @GetMapping
    public ResponseEntity<List<ItemPedidoModel>> listarItensPedido() {
        return ResponseEntity.ok(itemPedidoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoModel> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(itemPedidoService.buscarItemPedidoPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerItemPedido(@PathVariable Long id) {
        itemPedidoService.removerPorId(id);
        return ResponseEntity.noContent().build();
    }
}
