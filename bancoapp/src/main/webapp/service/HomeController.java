package src.main.webapp.service;

import src.main.webapp.domain.gerenciamento.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeController extends HttpServlet {

  int cont = 0;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      List<Usuario> listaUsuario = (new Usuario()).listar();
      Usuario usuario = new Usuario();
      usuario.setCPF("12345678911");
      usuario.setSenha("1234");
      usuario.setNome("Errico Malatesta");
      usuario.setEndereco("Av. Central, 13");
      usuario.setTelefone("21987132239");
//      usuario.inserir();
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
    response.sendRedirect(request.getContextPath() + "/login.jsp");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    this.cont++;
      response.sendRedirect("main");
  }
}
