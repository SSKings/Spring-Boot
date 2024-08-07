package com.sskings.api.gestor.financeiro.controllers;

import com.sskings.api.gestor.financeiro.dto.cartao.CartaoRequestDto;
import com.sskings.api.gestor.financeiro.dto.cartao.CartaoResponseDto;
import com.sskings.api.gestor.financeiro.exception.NotFoundException;
import com.sskings.api.gestor.financeiro.models.CartaoModel;
import com.sskings.api.gestor.financeiro.services.CartaoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cartao")
public class CartaoController {

    private final CartaoService cartaoService;

    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id){
        CartaoModel cartaoModel = cartaoService.findById(id)
                .orElseThrow(() -> new NotFoundException("Cartão não encontrado"));
        CartaoResponseDto response = new CartaoResponseDto(cartaoModel);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/u/{id}")
    public ResponseEntity<List<CartaoModel>> findByUsuarioId(@PathVariable(value = "id") UUID id){
        return ResponseEntity.ok(cartaoService.findByUsuarioId(id));
    }

    @PostMapping
    public ResponseEntity<CartaoResponseDto> save(@RequestBody @Valid CartaoRequestDto cartaoRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartaoService.save(cartaoRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartaoResponseDto> update(@PathVariable(value = "id") UUID id,
                                         @RequestBody @Valid CartaoRequestDto cartaoRequestDto){
        CartaoModel cartaoModel = cartaoService.findById(id)
                .orElseThrow(() -> new NotFoundException("Cartão não encontrado"));
        BeanUtils.copyProperties(cartaoRequestDto, cartaoModel);
        return ResponseEntity.status(HttpStatus.OK).body(cartaoService.update(cartaoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
        CartaoModel cartaoModel = cartaoService.findById(id)
                .orElseThrow(() -> new NotFoundException("Cartão não encontrado"));
        cartaoService.delete(cartaoModel);
        return ResponseEntity.status(HttpStatus.OK).body("Cartão deletado.");
    }
}
