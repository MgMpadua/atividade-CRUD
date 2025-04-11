package com.example.Atividade08_04.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nome;
    private String nomeAventureiro;

    @Enumerated(EnumType.STRING)
    private ClassePersonagem classe;

    private int level;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "personagem_id")
    private List<ItemMagico> itensMagicos = new ArrayList<>();

    private int forcaBase;
    private int defesaBase;

    public Personagem() {}

    public Personagem(String nome, String nomeAventureiro, ClassePersonagem classePersonagem, int level, int forca, int defesa) {
        if (forca + defesa > 10 || forca < 0 || defesa < 0) {
            throw new IllegalArgumentException("A soma de força e defesa deve ser no máximo 10");
        }
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classePersonagem;
        this.level = level;
        this.forcaBase = forca;
        this.defesaBase = defesa;
    }

    public boolean adicionarItemMagico(ItemMagico item) {
        if (item.getTipo() == ItemMagico.TipoItem.amuleto && temAmuleto()) {
            return false;
        }
        itensMagicos.add(item);
        return true;
    }

    public void removerItemMagico(Long itemId) {
        itensMagicos.removeIf(item -> item.getId().equals(itemId));
    }

    private boolean temAmuleto() {
        return itensMagicos.stream().anyMatch(item -> item.getTipo() == ItemMagico.TipoItem.amuleto);
    }

    public int getForcaTotal() {
        return forcaBase + itensMagicos.stream().mapToInt(ItemMagico::getForca).sum();
    }

    public int getDefesaTotal() {
        return defesaBase + itensMagicos.stream().mapToInt(ItemMagico::getDefesa).sum();
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getNomeAventureiro() { return nomeAventureiro; }
    public void setNomeAventureiro(String nomeAventureiro) { this.nomeAventureiro = nomeAventureiro; }
    public ClassePersonagem getClasse() { return classe; }
    public void setClasse(ClassePersonagem classePersonagem) { this.classe = classePersonagem; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public List<ItemMagico> getItensMagicos() { return itensMagicos; }
    public int getForcaBase() { return forcaBase; }
    public int getDefesaBase() { return defesaBase; }
}