<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="assets/default.css">
    <style>
        #login {
            padding-top: 1em;
        }
    </style>
    <title>Login</title>
</head>
<body>
<!-- Navbar header que fica sempre visível na parte superior -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/">
            <img
                    src="assets/falcao.svg"
                    width="30"
                    height="30"
                    class="d-inline-block align-top"
                    alt=""
            />
            Banco Falcão
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Alterna navegação">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Início</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Sobre</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Cadastro</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container mt-5 pt-5">
    <h1 class="text-center">Entre com seu CPF ou conta e a senha</h1>
    <form action="login" method="post" id="login">
        <div class="form-group row">
            <label for="nome">Nome</label>
            <input
                    type="text"
                    id="nome"
                    class="form-control"
                    value="12345678911"
                    name="nome"
                    aria-describedby="emailHelp"
                    placeholder="Nome"
            />
        </div>
        <div class="form-group row">
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