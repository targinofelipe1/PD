package com.gugawag.pdist.ejb.session;

import com.gugawag.pdist.modelo.Mensagem;
import com.gugawag.pdist.modelo.Usuario;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;


import java.util.List;

@Stateless(name = "usuarioService")
public class UsuarioService {
    @EJB
    private UsuarioDAO usuarioDAO;

    @EJB
    private MensagemService mensagemService;

    public void inserir(long id, String nome) {
        Usuario novoUsuario = new Usuario(id, nome);
        this.usuarioDAO.inserir(novoUsuario);

        if (id == 4) {
            mensagemService.inserir(id, "Mensagem u4");
        }
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = this.usuarioDAO.listar();

        for (Usuario usuario : usuarios) {
            List<Mensagem> mensagens = mensagemService.listarPorUsuario(usuario);
            usuario.setMensagens(mensagens);
        }

        return usuarios;
    }
}
