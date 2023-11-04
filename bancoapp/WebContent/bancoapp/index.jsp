<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="utf-8" %>
<%!
    public String exibirString(String s) {
        String retorno = "";
        return s != null && !s.isEmpty() ? s : retorno;
    }
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="assets/default.css">
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container mt-5 pt-5">
    <div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light">
        Olá,
        <%= exibirString((String) session.getAttribute("nome")) %>
        <%
            Hashtable<String, Object> opcoes = (Hashtable<String, Object>) session.getAttribute("acessos");
            String decisaoAction = "perfil-acesso";
            if (opcoes == null) {
                opcoes = (Hashtable<String, Object>) session.getAttribute("contas");
                decisaoAction = "conta";
            }
            if (opcoes != null) { %>
        <p>
            Selecione qual <% if (decisaoAction != "conta") { %>perfil de acesso:<% } else { %>conta:<% } %>
        </p>
        <div class="row">
            <% for (Map.Entry<String, Object> opcao :
                    opcoes.entrySet()) { %>
            <div class="col">
                <form action="acesso" method="post">
                    <input type="hidden" name="action" value="<%= decisaoAction %>"/>
                    <button type="submit" class="btn btn-outline-secondary" name="valor" value="<%= opcao.getValue() %>">
                        <%= opcao.getKey() %>
                    </button>
                </form>
            </div>
            <% } %>
        </div>
        <% } %>
        <%@include file="funcionalidades.jsp" %>
    </div>
</div>
</body>
</html>
