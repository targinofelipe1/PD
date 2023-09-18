package com.gugawag.pdist.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Mensagem {

    @Id
    private long id;
    private String texto;

    @ManyToOne
    @JoinColumn(name = "usuario_id") // Nome da coluna que faz a ligação entre Mensagem e Usuario
    private Usuario usuario;

    public Mensagem() {
    }

    public Mensagem(long id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
