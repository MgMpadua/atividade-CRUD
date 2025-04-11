package com.example.Atividade08_04.service;

import com.example.Atividade08_04.dto.ItemMagicoDTO;
import com.example.Atividade08_04.model.ItemMagico;
import com.example.Atividade08_04.repository.ItemMagicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemMagicoService {
    @Autowired
    private ItemMagicoRepository repository;

    public ItemMagico cadastrarItemMagico(ItemMagico item) {
        if (item.getTipo() == ItemMagico.TipoItem.arma && item.getDefesa() != 0) {
            throw new IllegalArgumentException("Itens do tipo ARMA não podem ter defesa diferente de 0");
        }
        if (item.getTipo() == ItemMagico.TipoItem.armadura && item.getForca() != 0) {
            throw new IllegalArgumentException("Itens do tipo ARMADURA não podem ter força diferente de 0");
        }
        return repository.save(item);
    }

    public List<ItemMagicoDTO> listarItensMagicos() {
        return repository.findAll().stream()
                .map(item -> new ItemMagicoDTO(item.getId(), item.getNome(), item.getTipo().name(),
                        item.getForca(), item.getDefesa()))
                .collect(Collectors.toList());
    }

    public ItemMagicoDTO buscarItemPorId(Long id) {
        return repository.findById(id)
                .map(item -> new ItemMagicoDTO(item.getId(), item.getNome(), item.getTipo().name(),
                        item.getForca(), item.getDefesa()))
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));
    }

    public ItemMagico buscarItemPorIdEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));
    }
}