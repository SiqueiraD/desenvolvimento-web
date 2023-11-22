<%@ page import="src.main.webapp.domain.gerenciamento.Usuario" %>
<%@ page import="src.main.webapp.service.GerenciamentoAppService" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="utf-8" %>
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
    <script>
        function CadastrarConta(url) {
            var xhttp = new XMLHttpRequest();
            xhttp.open("GET", url, true);
            xhttp.send();
        }
    </script>
</head>
<body>
<%@include file="../../header.jsp" %>
<div class="container mt-5 pt-5">
    <div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light">
        REQUISIÇÕES DE ABERTURA DE CONTA
        <table class="table-striped table-hover col-12">
            <tr>
                <th>Nome</th>
                <th>Ações</th>
            </tr>
            <%
                List<Usuario> listaUsu = GerenciamentoAppService.ListarUsuariosSolicitandoConta();
                if (!listaUsu.isEmpty()) {
                    for (Usuario u :
                            listaUsu) {

            %>
            <tr>
                <td>
                    <%= u.getNome()%>
                </td>
                <td>
                    <button type="button" class="btn btn-success" onclick="CadastrarConta('/conta/cadastrar/<%= u.getValorId()%>')">Criar conta</button>
                </td>
            </tr>
            <%
                    }
                }

            %>
        </table>
        <%
            String erro = (String) session.getAttribute("mensagemErro");
            if (erro != null) {
                out.println(erro);
                out.println("<br />");
                session.setAttribute("mensagemErro", null);
            }
        %>
    </div>
</div>
</body>
</html>
