<%@ page import="src.main.webapp.domain.seguranca.Funcionalidade" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="utf-8" %>
<!-- Navbar header que fica sempre visível na parte superior -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <div class="col">
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
        </div>
        <div id="tempoRestante" class="col align-self-end"></div>
        <div class="col">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Alterna navegação">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <% if (session.getAttribute("token") == null) { %>
                <li class="nav-item">
                    <a class="nav-link" href="/">Início</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Entrar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/cadastro">Cadastro</a>
                </li>
                <% } else { %>
                <li class="nav-item">
                    <a class="nav-link" href="/acesso/index.jsp">Início</a>
                </li>
                <% if (session.getAttribute("token").toString().split(":").length > 2) { %>
                <form id="sair-post" method="post" action="acesso"><input type="hidden" name="action" value="sair"/></form>
                <form id="trocar-post" method="post" action="acesso"><input type="hidden" name="action" value="trocar"/></form>
                <li class="nav-item">
                    <a class="nav-link" href="#" onclick="document.getElementById('trocar-post').submit();" >Trocar acesso</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#" onclick="document.getElementById('sair-post').submit();" methods="post">Sair</a>
                </li>
                <% } %>
                <% } %>
            </ul>
        </div>

    </div>
    <script>
        // Função que recebe um número de segundos e retorna uma string no formato hh:mm:ss
        function formatarTempo(segundosRestantes) {
            // Calcula as horas, minutos e segundos
            let horas = Math.floor(segundosRestantes / 3600);
            let minutos = Math.floor((segundosRestantes % 3600) / 60);
            let segundos = segundosRestantes % 60;
            // Adiciona um zero à esquerda se necessário
            if (horas < 10) horas = "0" + horas;
            if (minutos < 10) minutos = "0" + minutos;
            if (segundos < 10) segundos = "0" + segundos;
            // Retorna a string formatada
            return horas + ":" + minutos + ":" + segundos;
        }

        // Função que recebe um número de segundos e cria um contador regressivo
        function criarContador(segundosRestantes) {
            // Cria um elemento HTML para mostrar o tempo
            let elemento = document.getElementById("tempoRestante");
            elemento.id = "contador";
            elemento.style.fontFamily = "monospace";
            elemento.style.textAlign = "center";
            // Atualiza o elemento com o tempo inicial
            elemento.textContent = formatarTempo(segundosRestantes);
            // Cria uma variável para armazenar o identificador do intervalo
            let intervalo;

            // Cria uma função para atualizar o contador a cada segundo
            function atualizarContador() {
                // Diminui um segundo
                segundosRestantes--;
                // Atualiza o elemento com o novo tempo
                elemento.textContent = formatarTempo(segundosRestantes);
                // Se o tempo chegou a zero, limpa o intervalo e mostra uma mensagem
                if (segundosRestantes == 0) {
                    clearInterval(intervalo);
                }
            }

            // Cria um intervalo que chama a função de atualizar o contador a cada 1000 milissegundos
            intervalo = setInterval(atualizarContador, 1000);
        }

        // Chama a função de criar o contador com o parâmetro desejado (em segundos)
        <%  if (session.getAttribute("tempoRestante") != null) { %>
        criarContador(<%=  session.getAttribute("tempoRestante") != null ? session.getAttribute("tempoRestante") : "" %>);
        <% }%>
    </script>
</nav>