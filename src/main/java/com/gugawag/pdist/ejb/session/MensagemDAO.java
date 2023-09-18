package com.gugawag.pdist.ejb.session;

import com.gugawag.pdist.modelo.Mensagem;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class MensagemDAO {

    @PersistenceContext(unitName = "bd2")
    private EntityManager em;

    public void inserir(Mensagem mensagem) {
        em.persist(mensagem);
    }

    public List<Mensagem> listar() {
        return em.createQuery("SELECT m FROM Mensagem m", Mensagem.class).getResultList();
    }

    public Mensagem pesquisarPorId(long id) {
        return em.find(Mensagem.class, id);
    }
}
