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
    <title>Cadastro</title>
</head>
<body>
<%@include file="../../header.jsp"%>

<!-- Formulário de cadastro com os campos: cpf, nome, senha, email, endereço, telefone -->
<div class="container mt-5 pt-5">
    <h1 class="text-center">Formulário de Cadastro de Conta</h1>
    <form action="#" method="post">
        <div class="form-group row">
            <label for="cpf" class="col-sm-2 col-form-label">CPF</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="cpf" name="cpf" placeholder="Digite seu CPF" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="nome" class="col-sm-2 col-form-label">Nome</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="nome" name="nome" placeholder="Digite seu nome completo" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="senha" class="col-sm-2 col-form-label">Senha</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="senha" name="senha" placeholder="Digite uma senha forte" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="email" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="email" name ="email" placeholder ="Digite seu email válido " required >
            </div >
        </div >
        <div class ="form-group row ">
            <label for ="endereco "class ="col-sm-2 col-form-label ">Endereço</label >
            <div class ="col-sm-10 ">
                <input type ="text "class ="form-control "id ="endereco "name ="endereco "placeholder ="Digite seu endereço completo "required >
            </div >
        </div >
        <div class ="form-group row ">
            <label for ="telefone "class ="col-sm-2 col-form-label ">Telefone</label >
            <div class ="col-sm-10 ">
                <input type ="tel "class ="form-control "id ="telefone "name ="telefone "placeholder ="Digite seu número de telefone com DDD "required >
            </div >
        </div >
        <!-- Botão para enviar o formulário -->
        <button type ="submit "class ="btn btn-primary btn-block ">Enviar</button >
    </form >
</div >


</body>
</html>
