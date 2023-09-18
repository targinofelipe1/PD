package com.gugawag.pdist.ejb.session;

import com.gugawag.pdist.modelo.Mensagem;
import com.gugawag.pdist.modelo.Usuario;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless(name = "mensagemService")
public class MensagemService {


    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @EJB
    private MensagemDAO mensagemDAO;

    public void inserir(long id, String texto) {
        Mensagem novaMensagem = new Mensagem(id, texto);
        this.mensagemDAO.inserir(novaMensagem);
    }

    public List<Mensagem> listar() {
        return em.createQuery("SELECT m FROM Mensagem m", Mensagem.class).getResultList();
    }

    public List<Mensagem> listarPorUsuario(Usuario usuario) {
        return em.createQuery("SELECT m FROM Mensagem m WHERE m.usuario = :usuario", Mensagem.class)
                .setParameter("usuario", usuario)
                .getResultList();
    }
    public Mensagem pesquisarPorId(long id) {
        return this.mensagemDAO.pesquisarPorId(id);
    }
}
