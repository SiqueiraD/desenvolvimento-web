package src.main.webapp.servlets;

import src.main.webapp.domain.gerenciamento.Acesso;
import src.main.webapp.domain.seguranca.Perfil;
import src.main.webapp.service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MainController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getSession().setAttribute("mensagemErroAcesso", "Você não tem permissão para realizar essa ação.");
    req.getRequestDispatcher( "/bancoapp/index.jsp" ).forward(req, resp);
  }
}
