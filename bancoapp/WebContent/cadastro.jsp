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
</head>
<body>
<%@include file="header.jsp" %>
<!-- Formulário de cadastro com os campos: cpf, nome, senha, email, endereço, telefone -->
<div class="container mt-5 pt-5">
    <div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light">
        <h1 class="text-center">Formulário de Cadastro</h1>
        <form action="cadastro" method="post">
            <div class="form-group row">
                <label for="cpf" class="col-sm-2 col-form-label">CPF</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="cpf" name="cpf" placeholder="Digite seu CPF" required>
                </div>
            </div>
            <div class="form-group row">
                <label for="nome" class="col-sm-2 col-form-label">Nome</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="nome" name="nome" placeholder="Digite seu nome completo"
                           required>
                </div>
            </div>
            <div class="form-group row">
                <label for="senha" class="col-sm-2 col-form-label">Senha</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="senha" name="senha"
                           placeholder="Digite uma senha forte" required>
                </div>
            </div>
            <div class="form-group row">
                <label for="email" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="email" name="email"
                           placeholder="Digite seu email válido " required>
                </div>
            </div>
            <div class="form-group row ">
                <label for="endereco " class="col-sm-2 col-form-label ">Endereço</label>
                <div class="col-sm-10 ">
                    <input type="text " class="form-control " id="endereco " name="endereco"
                           placeholder="Digite seu endereço completo " required>
                </div>
            </div>
            <div class="form-group row ">
                <label for="telefone " class="col-sm-2 col-form-label ">Telefone</label>
                <div class="col-sm-10 ">
                    <input type="tel " class="form-control " id="telefone " name="telefone"
                           placeholder="Digite seu número de telefone com DDD " required>
                </div>
            </div>
            <%
                String erro = (String) session.getAttribute("mensagemErro");
                if (erro != null) {
                    out.println(erro);
                    out.println("<br />");
                    session.setAttribute("mensagemErro", null);
                }
            %>
            <!-- Botão para enviar o formulário -->
            <button type="submit " class="btn btn-primary btn-block ">Enviar</button>
        </form>
    </div>
</div>


</body>
</html>
