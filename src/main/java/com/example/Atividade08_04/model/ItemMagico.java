package com.example.Atividade08_04.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class ItemMagico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    private int forca;
    private int defesa;

    public ItemMagico() {}

    public ItemMagico(String nome, TipoItem tipo, int forca, int defesa) {
        validarItem(nome, tipo, forca, defesa);
        this.nome = nome;
        this.tipo = tipo;
        this.forca = forca;
        this.defesa = defesa;
    }

    private void validarItem(String nome, TipoItem tipo, int forca, int defesa) {
        if (forca < 0 || defesa < 0 || forca > 10 || defesa > 10) {
            throw new IllegalArgumentException("Força e Defesa devem estar entre 0 e 10");
        }
        if (forca == 0 && defesa == 0) {
            throw new IllegalArgumentException("Item não pode ter força e defesa iguais a zero");
        }
        if (tipo == TipoItem.arma && defesa != 0) {
            throw new IllegalArgumentException("Armas não podem ter defesa diferente de zero");
        }
        if (tipo == TipoItem.armadura && forca != 0) {
            throw new IllegalArgumentException("Armaduras não podem ter força diferente de zero");
        }
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public TipoItem getTipo() { return tipo; }
    public void setTipo(TipoItem tipo) { this.tipo = tipo; }
    public int getForca() { return forca; }
    public void setForca(int forca) { this.forca = forca; }
    public int getDefesa() { return defesa; }
    public void setDefesa(int defesa) { this.defesa = defesa; }

    public enum TipoItem {
        arma, armadura, amuleto
    }
}