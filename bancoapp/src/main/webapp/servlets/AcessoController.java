package src.main.webapp.servlets;

import src.main.webapp.domain.gerenciamento.Acesso;
import src.main.webapp.domain.seguranca.Funcionalidade;
import src.main.webapp.domain.seguranca.Perfil;
import src.main.webapp.service.GerenciamentoAppService;
import src.main.webapp.service.Perfis;
import src.main.webapp.service.SegurancaAppService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Hashtable;

@WebServlet("/acesso/*")
public class AcessoController extends HttpServlet {


    boolean validaAcesso(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (!SegurancaAppService.VerificarTempoAcesso(session)) {
            session.setAttribute("mensagemErro", "Acabou a sessão de acesso, entre novamente");
            resp.sendRedirect( "/login");
            return false;
        }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String newPath = path.replace(".jsp", "");
        if (!validaAcesso(req, resp))
            return;
        String token = (String) req.getSession().getAttribute("token");
        if (newPath.contains("funcionalidades")) {
            var idFuncionalidade = req.getParameter("idFuncionalidade");
        } else if (!newPath.contains("assets") && token.contains(":"))
            if (token.split(":").length > 2) {
                Acesso acesso = SegurancaAppService.PegarAcesso(token);
                Perfil perfil = acesso.getPerfil();
                if (Perfis.CLIENTE.getValor() == Integer.parseInt(perfil.getValorId().toString())) {
                    if (req.getSession().getAttribute("idConta") != null) {
                        req.getSession().setAttribute("funcionalidades", acesso.getListaFuncionalidade());
                    } else {
                        Hashtable<String, Object> objContas = GerenciamentoAppService.ContasPerfilAcesso(Integer.parseInt(acesso.getValorId().toString()));
                        if (objContas.size() > 1)
                            req.getSession().setAttribute("contas", objContas);
                        else
                            req.getSession().setAttribute("idConta", objContas.values().stream().findFirst().toString());
                    }
                } else
                    req.getSession().setAttribute("funcionalidades", acesso.getListaFuncionalidade());
            } else if (token.split(":").length > 1 && req.getSession().getAttribute("acessos") == null) {
                req.getSession().setAttribute("acessos", SegurancaAppService.ListarAcessoHashTableSession(Integer.parseInt(token.split(":")[1])));
            }
        newPath = !newPath.contains("assets") ? "/bancoapp" + newPath + ".jsp" : newPath;
        req.getRequestDispatcher(newPath).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!validaAcesso(req, resp))
            return;
        String action = req.getParameter("action");
        String valor = req.getParameter("valor");
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");
        switch (action) {
            case "perfil-acesso":
                session.setAttribute("token", token + ":" + valor);
                session.setAttribute("acessos", null);
                resp.sendRedirect(req.getContextPath() + "/acesso/index.jsp");
                break;

            case "conta":
                session.setAttribute("idConta", valor);
                session.setAttribute("contas", null);
                resp.sendRedirect(req.getContextPath() + "/acesso/index.jsp");
                break;
            case "funcionalidades":
                if (!SegurancaAppService.PermitirAcesso(session, Integer.parseInt(valor))) {
                    session.setAttribute("mensagemErroAcesso", "Você não tem permissão para realizar essa ação.");
                    resp.sendRedirect(req.getContextPath() + "/acesso/index.jsp");
                    return;
                } else
                    resp.sendRedirect(req.getContextPath() + "/acesso/funcionalidades" + "?idFuncionalidade=" + valor);

                break;
            case "sair":
                session.invalidate();
                resp.sendRedirect(req.getContextPath() + "/");
                break;
            case "trocar":
                token = token.split(":")[0] + ":" + token.split(":")[1];
                session.setAttribute("token", token);
                session.setAttribute("funcionalidades", null);
                resp.sendRedirect(req.getContextPath() + "/acesso/index.jsp");
                break;

        }
//                            for (Conta c :
//                                    listaConta) {
//                                acessos.append(c.getNumeroConta()).append("|");
//                            }
    }
}
