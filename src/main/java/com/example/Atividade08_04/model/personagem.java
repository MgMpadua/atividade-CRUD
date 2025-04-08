package com.example.Atividade08_04.model;
import

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class personagem {
    @Id
    @GeneratedValue
    private long id;


    private String nome;
    private String nomeAventureiro;
    private int lvl;
    private int forca;
    private int defesa;
    private int pontos;
    private int quantidadeItensMagicos;
    private List<classe> classe;
    private List<itemMagico> itemMagico;

    public personagem(long id, String nome, String nomeAventureiro, int lvl,
                      int forca, int defesa, int pontos, int quantidadeItensMagicos,
                      List<com.example.Atividade08_04.model.classe> classe,
                      List<com.example.Atividade08_04.model.itemMagico> itemMagico) {

        this.id = id;
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.lvl = lvl;
        this.forca = forca;
        this.defesa = defesa;
        this.pontos = pontos;
        this.quantidadeItensMagicos = quantidadeItensMagicos;
        this.classe = classe;
        this.itemMagico = itemMagico;
    }


    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public int getLvl() {
        return lvl;
    }

    public int getForca() {
        return forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getPontos() {
        return pontos;
    }

    public int getQuantidadeItensMagicos() {
        return quantidadeItensMagicos;
    }

    public List<com.example.Atividade08_04.model.classe> getClasse() {
        return classe;
    }

    public List<com.example.Atividade08_04.model.itemMagico> getItemMagico() {
        return itemMagico;
    }
}
