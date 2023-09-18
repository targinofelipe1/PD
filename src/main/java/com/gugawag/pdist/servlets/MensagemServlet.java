package com.gugawag.pdist.servlets;

import com.gugawag.pdist.ejb.session.MensagemService;
import com.gugawag.pdist.modelo.Mensagem;
import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/mensagem.do"})
public class MensagemServlet extends HttpServlet {

    @EJB(lookup = "java:module/mensagemService")
    private MensagemService mensagemService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String operacao = request.getParameter("oper");
        PrintWriter resposta = response.getWriter();
        switch (operacao) {
            case "1": { // inserir
                long id = Long.parseLong(request.getParameter("id"));
                String texto = request.getParameter("texto");
                mensagemService.inserir(id, texto);
                break;
            }
            case "2": { // listar
                List<Mensagem> mensagens = mensagemService.listar();
                for (Mensagem mensagem : mensagens) {
                    resposta.println("ID: " + mensagem.getId() + ", Texto: " + mensagem.getTexto());
                }
                break;
            }
            case "3": { // pesquisar por ID
                long id = Long.parseLong(request.getParameter("id"));
                Mensagem mensagem = mensagemService.pesquisarPorId(id);
                if (mensagem != null) {
                    resposta.println("ID: " + mensagem.getId() + ", Texto: " + mensagem.getTexto());
                } else {
                    resposta.println("Mensagem não encontrada.");
                }
                break;
            }
            default:
                resposta.println("Operação inválida.");
        }
    }
}

