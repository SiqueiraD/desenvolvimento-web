<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="utf-8" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.Map" %>
<%@ page import="src.main.webapp.domain.seguranca.Funcionalidade" %>
<%@ page import="java.util.List" %>
<%
    String funcAction = "funcionalidades";
    List<Funcionalidade> opcoesFunc = (List<Funcionalidade>) session.getAttribute(funcAction);
    if (opcoesFunc != null) { %>
<p>
    Selecione o serviço:
</p>
<div class="row">
    <% for (Funcionalidade opcao :
            opcoesFunc) { %>
    <div class="col">
        <form action="acesso" method="post">
            <input type="hidden" name="action" value="<%= funcAction %>"/>
            <button type="submit" class="btn btn-outline-secondary" name="valor" value="<%= opcao.getValorId() %>">
                <%= opcao.getNome() %>
            </button>
        </form>
    </div>
    <% } %>
</div>
<% } %>