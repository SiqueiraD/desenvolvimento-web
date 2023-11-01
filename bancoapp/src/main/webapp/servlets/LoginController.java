package src.main.webapp.servlets;

import src.main.webapp.domain.gerenciamento.Usuario;
import src.main.webapp.service.ClientException;
import src.main.webapp.service.SegurancaAppService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getHeader("mensagemErro") != null && !req.getHeader("mensagemErro").isEmpty())
            resp.addHeader("mensagemErro",req.getHeader("mensagemErro"));
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var segurancaAppService = new SegurancaAppService();
        var usuario = new Usuario();
        usuario.setCPF(req.getParameter("CPF"));
        usuario.setSenha(req.getParameter("senha"));
        String token = null;
        try {
            token = segurancaAppService.Logar(usuario);
            if (token.isEmpty()) {
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
                return;
            }
        } catch (ClientException e) {
            req.setAttribute("mensagemErro", e.getMessage());
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute("token", token);
        req.getRequestDispatcher( "/acesso/index.html").forward(req,resp);
//        resp.sendRedirect(req.getContextPath() + "/acesso/index.html");
    }
}
