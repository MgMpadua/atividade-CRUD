package com.example.Atividade08_04.controller;

import com.example.Atividade08_04.dto.ItemMagicoDTO;
import com.example.Atividade08_04.model.ItemMagico;
import com.example.Atividade08_04.service.ItemMagicoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-magicos")
public class ItemMagicoController {
    @Autowired
    private ItemMagicoService service;

    @PostMapping
    @Operation(summary = "Cadastrar um novo item mágico")
    public ResponseEntity<ItemMagico> cadastrarItemMagico(@RequestBody ItemMagico item) {
        return ResponseEntity.ok(service.cadastrarItemMagico(item));
    }

    @GetMapping
    @Operation(summary = "Listar todos os itens mágicos")
    public ResponseEntity<List<ItemMagicoDTO>> listarItensMagicos() {
        return ResponseEntity.ok(service.listarItensMagicos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar item mágico por ID")
    public ResponseEntity<ItemMagicoDTO> buscarItemPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarItemPorId(id));
    }
}