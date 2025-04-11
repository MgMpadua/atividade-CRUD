package com.example.Atividade08_04.service;

import com.example.Atividade08_04.dto.ItemMagicoDTO;
import com.example.Atividade08_04.dto.PersonagemDTO;
import com.example.Atividade08_04.model.ItemMagico;
import com.example.Atividade08_04.model.Personagem;
import com.example.Atividade08_04.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonagemService {
    @Autowired
    private PersonagemRepository repository;

    @Autowired
    private ItemMagicoService itemMagicoService;

    public Personagem cadastrarPersonagem(Personagem personagem, List<Long> itemIds) {
        if (personagem.getForcaBase() + personagem.getDefesaBase() > 10) {
            throw new IllegalArgumentException("A soma de força e defesa deve ser no máximo 10");
        }

        personagem.getItensMagicos().clear();

        if (itemIds != null && !itemIds.isEmpty()) {
            for (Long itemId : itemIds) {
                ItemMagico item = itemMagicoService.buscarItemPorIdEntity(itemId);
                if (!personagem.adicionarItemMagico(item)) {
                    throw new IllegalArgumentException("Não foi possível adicionar o item mágico com ID " + itemId + " (ex.: limite de amuleto atingido)");
                }
            }
        }

        return repository.save(personagem);
    }

    public List<PersonagemDTO> listarPersonagens() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PersonagemDTO buscarPorId(Long id) {
        return repository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado"));
    }

    public Personagem atualizarNomeAventureiro(Long id, String novoNomeAventureiro) {
        Personagem personagem = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado"));
        personagem.setNomeAventureiro(novoNomeAventureiro);
        return repository.save(personagem);
    }

    public void removerPersonagem(Long id) {
        repository.deleteById(id);
    }

    public boolean adicionarItemMagico(Long personagemId, Long itemId) {
        Personagem personagem = repository.findById(personagemId)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado"));
        ItemMagico item = itemMagicoService.buscarItemPorIdEntity(itemId);
        if (personagem.adicionarItemMagico(item)) {
            repository.save(personagem);
            return true;
        }
        return false;
    }

    public List<ItemMagicoDTO> listarItensPorPersonagem(Long personagemId) {
        Personagem personagem = repository.findById(personagemId)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado"));
        return personagem.getItensMagicos().stream()
                .map(item -> new ItemMagicoDTO(item.getId(), item.getNome(), item.getTipo().name(),
                        item.getForca(), item.getDefesa()))
                .collect(Collectors.toList());
    }

    public void removerItemMagico(Long personagemId, Long itemId) {
        Personagem personagem = repository.findById(personagemId)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado"));
        personagem.removerItemMagico(itemId);
        repository.save(personagem);
    }

    public ItemMagicoDTO buscarAmuleto(Long personagemId) {
        Personagem personagem = repository.findById(personagemId)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado"));
        return personagem.getItensMagicos().stream()
                .filter(item -> item.getTipo() == ItemMagico.TipoItem.amuleto)
                .findFirst()
                .map(item -> new ItemMagicoDTO(item.getId(), item.getNome(), item.getTipo().name(),
                        item.getForca(), item.getDefesa()))
                .orElse(null);
    }

    private PersonagemDTO toDTO(Personagem personagem) {
        List<ItemMagicoDTO> itensDTO = personagem.getItensMagicos().stream()
                .map(item -> new ItemMagicoDTO(item.getId(), item.getNome(), item.getTipo().name(),
                        item.getForca(), item.getDefesa()))
                .collect(Collectors.toList());
        return new PersonagemDTO(personagem.getId(), personagem.getNome(), personagem.getNomeAventureiro(),
                personagem.getClasse().name(), personagem.getLevel(), personagem.getForcaBase(),
                personagem.getDefesaBase(), personagem.getForcaTotal(), personagem.getDefesaTotal(), itensDTO);
    }
}