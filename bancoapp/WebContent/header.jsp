<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="utf-8" %>
<%!
    public String nomeLogado(String nome) {
        String retorno = "";
        return nome != null && !nome.isEmpty() ? nome: retorno;
    }
%>
<!-- Navbar header que fica sempre visível na parte superior -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/">
            <img
                    src="assets/falcao.svg"
                    width="30"
                    height="30"
                    class="d-inline-block align-top bancofalcao"
                    alt=""
            />
            Banco Falcão
        </a>
        <%= nomeLogado((String)session.getAttribute("nome")) %>
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