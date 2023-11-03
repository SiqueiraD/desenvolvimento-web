package src.main.webapp.servlets;

import src.main.webapp.domain.gerenciamento.Usuario;
import src.main.webapp.service.SegurancaAppService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

@WebFilter(filterName = "AcessoFiltro", urlPatterns = {"/acesso/*"})
public class AcessoFiltro implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        var token = ((HttpServletRequest) request).getSession().getAttribute("token");

        if (token != null && new SegurancaAppService().VerificarTokenValido(token.toString())) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/login");
        }
    }
    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
