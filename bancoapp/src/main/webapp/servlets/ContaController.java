package src.main.webapp.servlets;

import src.main.webapp.domain.gerenciamento.Usuario;
import src.main.webapp.service.ClientException;
import src.main.webapp.service.GerenciamentoAppService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/conta/*")
public class ContaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        //TODO: path = '/cadastrar/{idUsuario}' | Realizar cadastro de conta a partir de um usuário
        String men = req.getSession().getAttribute("mensagemErro") != null ?
                req.getSession().getAttribute("mensagemErro").toString() : null;
        req.getSession().invalidate();
        if (men != null)
            req.getSession().setAttribute("mensagemErro", men);
        req.getRequestDispatcher("/cadastro.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var cpf = req.getParameter("cpf");
        var nome = req.getParameter("nome");
        var senha = req.getParameter("senha");
        var email = req.getParameter("email");
        var endereco = req.getParameter("endereco");
        var telefone = req.getParameter("telefone");
        try {
            var novoUsuario = new Usuario(cpf, nome, email, senha, endereco, telefone);
            GerenciamentoAppService.CadastrarUsuario(novoUsuario);
            req.getRequestDispatcher("/aguarde.jsp").forward(req, resp);
        } catch (ClientException e) {
            String mensagem = e.getMessage();
            req.getSession().setAttribute("mensagemErro", mensagem);
            req.getRequestDispatcher("/cadastro.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/aguarde.jsp");
    }
}
