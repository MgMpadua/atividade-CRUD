package com.example.Atividade08_04.controller;

import com.example.Atividade08_04.dto.ItemMagicoDTO;
import com.example.Atividade08_04.dto.PersonagemCrearDTO;
import com.example.Atividade08_04.dto.PersonagemDTO;
import com.example.Atividade08_04.model.Personagem;
import com.example.Atividade08_04.service.ItemMagicoService;
import com.example.Atividade08_04.service.PersonagemService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @Autowired
    private ItemMagicoService itemMagicoService;

    @PostMapping
    @Operation(summary = "Cadastrar um novo personagem com itens mágicos existentes", description = "Cria um personagem e associa itens mágicos existentes com base nos IDs fornecidos. " +
                    "O campo 'itensMagicos' não deve ser incluído; use 'itemIds' para selecionar itens. " +
                    "A resposta não inclui 'forcaTotal' nem 'defesaTotal'.")
    public ResponseEntity<PersonagemCrearDTO> cadastrarPersonagem(@RequestBody PersonagemRequest personagemRequest) {
        Personagem personagem = personagemRequest.getPersonagem();
        List<Long> itemIds = personagemRequest.getItemIds();
        Personagem personagemSalvo = personagemService.cadastrarPersonagem(personagem, itemIds);
        return ResponseEntity.ok(toCreateDTO(personagemSalvo));
    }

    @GetMapping
    @Operation(summary = "Listar todos os personagens")
    public ResponseEntity<List<PersonagemDTO>> listarPersonagens() {
        return ResponseEntity.ok(personagemService.listarPersonagens());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar personagem por ID")
    public ResponseEntity<PersonagemDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.buscarPorId(id));
    }

    @PutMapping("/{id}/nome-aventureiro")
    @Operation(summary = "Atualizar nome aventureiro por ID")
    public ResponseEntity<PersonagemDTO> atualizarNomeAventureiro(@PathVariable Long id, @RequestParam String novoNome) {
        Personagem personagemAtualizado = personagemService.atualizarNomeAventureiro(id, novoNome);
        return ResponseEntity.ok(toDTO(personagemAtualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover personagem por ID")
    public ResponseEntity<Void> removerPersonagem(@PathVariable Long id) {
        personagemService.removerPersonagem(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/itens/{itemId}")
    @Operation(summary = "Adicionar item mágico ao personagem")
    public ResponseEntity<String> adicionarItemMagico(@PathVariable Long id, @PathVariable Long itemId) {
        return personagemService.adicionarItemMagico(id, itemId) ?
                ResponseEntity.ok("Item adicionado com sucesso!") :
                ResponseEntity.badRequest().body("Falha ao adicionar item!");
    }

    @GetMapping("/{id}/itens")
    @Operation(summary = "Listar itens mágicos de um personagem")
    public ResponseEntity<List<ItemMagicoDTO>> listarItensPorPersonagem(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.listarItensPorPersonagem(id));
    }

    @DeleteMapping("/{id}/itens/{itemId}")
    @Operation(summary = "Remover item mágico de um personagem")
    public ResponseEntity<Void> removerItemMagico(@PathVariable Long id, @PathVariable Long itemId) {
        personagemService.removerItemMagico(id, itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/amuleto")
    @Operation(summary = "Buscar amuleto do personagem")
    public ResponseEntity<ItemMagicoDTO> buscarAmuleto(@PathVariable Long id) {
        ItemMagicoDTO amuleto = personagemService.buscarAmuleto(id);
        return amuleto != null ? ResponseEntity.ok(amuleto) : ResponseEntity.noContent().build();
    }

    @GetMapping("/itens-disponiveis")
    @Operation(summary = "Listar todos os itens mágicos disponíveis")
    public ResponseEntity<List<ItemMagicoDTO>> listarItensDisponiveis() {
        return ResponseEntity.ok(itemMagicoService.listarItensMagicos());
    }

    private PersonagemDTO toDTO(Personagem personagem) {
        List<ItemMagicoDTO> itensDTO = personagem.getItensMagicos().stream()
                .map(item -> new ItemMagicoDTO(item.getId(), item.getNome(), item.getTipo().name(),
                        item.getForca(), item.getDefesa()))
                .toList();
        return new PersonagemDTO(personagem.getId(), personagem.getNome(), personagem.getNomeAventureiro(),
                personagem.getClasse().name(), personagem.getLevel(), personagem.getForcaBase(),
                personagem.getDefesaBase(), personagem.getForcaTotal(), personagem.getDefesaTotal(), itensDTO);
    }


    private PersonagemCrearDTO toCreateDTO(Personagem personagem) {
        List<ItemMagicoDTO> itensDTO = personagem.getItensMagicos().stream()
                .map(item -> new ItemMagicoDTO(item.getId(), item.getNome(), item.getTipo().name(),
                        item.getForca(), item.getDefesa()))
                .toList();
        return new PersonagemCrearDTO(personagem.getId(), personagem.getNome(), personagem.getNomeAventureiro(),
                personagem.getClasse().name(), personagem.getLevel(), personagem.getForcaBase(),
                personagem.getDefesaBase(), itensDTO);
    }


    @JsonIgnoreProperties({"itensMagicos"})
    static class PersonagemRequest {
        private Personagem personagem;
        private List<Long> itemIds;

        public Personagem getPersonagem() {
            return personagem;
        }

        public void setPersonagem(Personagem personagem) {
            this.personagem = personagem;
        }

        public List<Long> getItemIds() {
            return itemIds;
        }

        public void setItemIds(List<Long> itemIds) {
            this.itemIds = itemIds;
        }
    }
}