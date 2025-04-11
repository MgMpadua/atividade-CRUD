package com.example.Atividade08_04.dto;

import java.util.List;

public class PersonagemDTO {
    private Long id;
    private String nome;
    private String nomeAventureiro;
    private String classe;
    private int level;
    private int forcaBase;
    private int defesaBase;
    private int forcaTotal;
    private int defesaTotal;
    private List<ItemMagicoDTO> itensMagicos;

    public PersonagemDTO(Long id, String nome, String nomeAventureiro, String classe, int level,
                         int forcaBase, int defesaBase, int forcaTotal, int defesaTotal,
                         List<ItemMagicoDTO> itensMagicos) {
        this.id = id;
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.forcaBase = forcaBase;
        this.defesaBase = defesaBase;
        this.forcaTotal = forcaTotal;
        this.defesaTotal = defesaTotal;
        this.itensMagicos = itensMagicos;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getNomeAventureiro() { return nomeAventureiro; }
    public String getClasse() { return classe; }
    public int getLevel() { return level; }
    public int getForcaBase() { return forcaBase; }
    public int getDefesaBase() { return defesaBase; }
    public int getForcaTotal() { return forcaTotal; }
    public int getDefesaTotal() { return defesaTotal; }
    public List<ItemMagicoDTO> getItensMagicos() { return itensMagicos; }
}