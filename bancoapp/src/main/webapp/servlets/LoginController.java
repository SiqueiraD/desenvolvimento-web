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
import java.util.Hashtable;
import java.util.Map;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String men = req.getSession().getAttribute("mensagemErro") != null ?
                req.getSession().getAttribute("mensagemErro").toString() : null;
        req.getSession().invalidate();
        if (men != null)
            req.getSession().setAttribute("mensagemErro", men);
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var cpf = req.getParameter("CPF");
        var senha = req.getParameter("senha");
        Hashtable<String, Object> objSessions = null;
        HttpSession session = req.getSession();
        try {
            if (cpf == null || senha == null || cpf.isEmpty() || senha.isEmpty()) {
                req.getSession().setAttribute("mensagemErro", "Deve preencher os campos corretamente");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }
            objSessions = SegurancaAppService.Logar(cpf, senha);
            String token = (String) objSessions.get("token");
            if (token.isEmpty()) {
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }
        } catch (ClientException e) {
            String mensagem = e.getMessage();
            req.getSession().setAttribute("mensagemErro", mensagem);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        for (Map.Entry<String, Object> map :
                objSessions.entrySet()) {
            session.setAttribute(map.getKey(), map.getValue());
        }
//        req.getRequestDispatcher("/acesso/index").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/acesso/index.jsp");
    }
}
