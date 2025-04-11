package com.example.Atividade08_04.dto;

public class ItemMagicoDTO {
    private Long id;
    private String nome;
    private String tipo;
    private int forca;
    private int defesa;

    public ItemMagicoDTO(Long id, String nome, String tipo, int forca, int defesa) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.forca = forca;
        this.defesa = defesa;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public int getForca() { return forca; }
    public int getDefesa() { return defesa; }
}