package com.example.Atividade08_04.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class itemMagico {
    @Id
    @GeneratedValue
    private long id;

    private int forcaAmuleto;
    private int defesaAmuleto;
    private String tipo;
    private String nomeItemMagico;

    public itemMagico(long id, int forcaAmuleto, int defesaAmuleto, String tipo,
                      String nomeItemMagico) {
        this.id = id;
        this.forcaAmuleto = forcaAmuleto;
        this.defesaAmuleto = defesaAmuleto;
        this.tipo = tipo;
        this.nomeItemMagico = nomeItemMagico;
    }

    public long getId() {
        return id;
    }

    public int getForcaAmuleto() {
        return forcaAmuleto;
    }

    public int getDefesaAmuleto() {
        return defesaAmuleto;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNomeItemMagico() {
        return nomeItemMagico;
    }
}
