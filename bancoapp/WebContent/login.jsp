<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="utf-8"  %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="assets/default.css">
    <style>
        #login {
            padding-top: 1em;
        }
    </style>
    <title>Login</title>
</head>
<body>
<%@include file="header.jsp"%>

<div class="container mt-5 pt-5">
    <h1 class="text-center">Entre com seu CPF ou conta e a senha</h1>
    <form action="login" method="post" id="login">
        <div class="form-group">
            <label for="CPF">CPF</label>
            <input
                    type="text"
                    id="CPF"
                    class="form-control"
                    value="12345678911"
                    name="CPF"
                    aria-describedby="emailHelp"
                    placeholder="Nome"
            />
        </div>
        <div class="form-group">
            <label for="senha">Senha</label>
            <input
                    type="password"
                    id="senha"
                    class="form-control"
                    name="senha"
                    value="1234"
                    placeholder="Password"
            />
            <label
                    style="font-size: large; font-weight: bolder; color: red"
            >
                <%
                    String erro = (String) request.getAttribute("erros");
                    if (erro != null) {
                        out.println(erro);
                        out.println("<br />");
                    }
                %>
            </label>
        </div>
        <input type="hidden" name="parametro" value="login"/>
        <button type="submit" value="Logar" class="btn btn-primary">
            Entrar
        </button>
    </form>
</div>
</body>
</html>
