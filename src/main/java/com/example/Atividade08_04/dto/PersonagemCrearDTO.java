package com.example.Atividade08_04.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

public class PersonagemCrearDTO {
    private Long id;
    private String nome;
    private String nomeAventureiro;
    private String classe;
    private int level;
    private int forcaBase;
    private int defesaBase;
    private List<ItemMagicoDTO> itensMagicos;

    public PersonagemCrearDTO() {}


    public PersonagemCrearDTO(Long id, String nome, String nomeAventureiro, String classe, int level,
                               int forcaBase, int defesaBase, List<ItemMagicoDTO> itensMagicos) {
        this.id = id;
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.forcaBase = forcaBase;
        this.defesaBase = defesaBase;
        this.itensMagicos = itensMagicos;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getNomeAventureiro() { return nomeAventureiro; }
    public void setNomeAventureiro(String nomeAventureiro) { this.nomeAventureiro = nomeAventureiro; }
    public String getClasse() { return classe; }
    public void setClasse(String classe) { this.classe = classe; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public int getForcaBase() { return forcaBase; }
    public void setForcaBase(int forcaBase) { this.forcaBase = forcaBase; }
    public int getDefesaBase() { return defesaBase; }
    public void setDefesaBase(int defesaBase) { this.defesaBase = defesaBase; }
    public List<ItemMagicoDTO> getItensMagicos() { return itensMagicos; }
    public void setItensMagicos(List<ItemMagicoDTO> itensMagicos) { this.itensMagicos = itensMagicos; }
}