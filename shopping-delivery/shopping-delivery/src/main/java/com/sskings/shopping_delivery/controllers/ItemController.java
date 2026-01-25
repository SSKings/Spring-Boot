package com.sskings.shopping_delivery.controllers;

import com.sskings.shopping_delivery.models.ItemModel;
import com.sskings.shopping_delivery.services.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemModel> salvarItem(@Valid @RequestBody ItemModel item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.salvar(item));
    }

    @GetMapping
    public ResponseEntity<List<ItemModel>> listarItens() {
        return ResponseEntity.ok(itemService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemModel> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemModel> atualizarItem(@PathVariable Long id, 
                                                   @Valid @RequestBody ItemModel item) {
        return ResponseEntity.ok(itemService.atualizar(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerItem(@PathVariable Long id) {
        itemService.removerPorId(id);
        return ResponseEntity.noContent().build();
    }
}
